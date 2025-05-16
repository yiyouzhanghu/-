<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/chongwufuwu?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.fuwumingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang04"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">取消已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="item">
						<div class="lable">服务类型</div>
						<div class="text "  >{{detail.fuwuleixing}}</div>
					</div>
					<div class="item">
						<div class="lable">人数</div>
						<div class="text "  >{{detail.columns}}</div>
					</div>
					<div class="item">
						<div class="lable">次数</div>
						<div class="text "  >{{detail.rows}}</div>
					</div>
					<div class="item">
						<div class="lable">总数</div>
						<div class="text "  >{{detail.totals}}</div>
					</div>
					<div class="item">
						<div class="lable">可约时间</div>
						<div class="text "  >{{detail.opentime}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('chongwufuwu','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('chongwufuwu','删除')" @click="delClick">删除</el-button>
						<el-button class="reserveBtn" v-if="btnAuth('chongwufuwu','跨表预状态-预约')" @click="yuyueClick('fuwuyuyue')" type="warning">预约</el-button>
					</div>
				</div>
			</div>
		
			<el-carousel v-if="detailBanner.length" trigger="click" indicator-position="inside" arrow="hover" type="default" direction="horizontal" height="480px" :autoplay="false" :interval="3000" :loop="true">
				<el-carousel-item v-for="item in detailBanner" :key="item.id">
					<img :preview-src-list="[item]" v-if="item.substr(0,4)=='http'" :src="item" class="image">
					<img :preview-src-list="[baseUrl + item]" v-else :src="baseUrl + item" class="image">
				</el-carousel-item>
			</el-carousel>


		

		
			<el-tabs class="detail-tabs" v-model="activeName" type="border-card">
				<el-tab-pane label="服务介绍" name="first">
					<div v-html="detail.fuwujieshao"></div>
				</el-tab-pane>
				<el-tab-pane label="评论" name="second">
					<el-form class="add commentForm" :model="form" :rules="rules" ref="form">
						<el-form-item class="item" label="评论" prop="content">
							<editor
								v-model="form.content" 
								class="editor" 
								action="file/upload">
							</editor>
						</el-form-item>
						<el-form-item class="commentBtn">
							<el-button class="submitBtn" type="primary" @click="submitForm('form')">立即提交</el-button>
							<el-button class="resetBtn" @click="resetForm('form')">重置</el-button>
						</el-form-item>
					</el-form>
				
					<div v-if="infoList.length" class="comment-list">
						<div class="comment-item" v-for="item in infoList" :key="item.id" @mouseenter="discussEnter(item.id)"
							@mouseleave="discussLeave">
							<div class="istop" v-if="item.istop">
								<span class="icon iconfont icon-jiantou24"></span>
							</div>
							<div class="user">
								<el-image v-if="item.avatarurl" :size="50" :src="baseUrl + item.avatarurl"></el-image>
								<el-image v-if="!item.avatarurl" :size="50" :src="require('@/assets/touxiang.png')"></el-image>
								<div class="name">{{item.nickname}}</div>
							</div>
							<div class="comment-content-box">
								<div v-html="item.content"></div>
								<div class="zancai-box">
									<div v-if="!comcaiChange(item)" class="zan-item" :class="comzanChange(item)?'active':''" @click="comzanClick(item)">
										<span class="icon iconfont" :class="comzanChange(item)?'icon-zan11':'icon-zan19'"></span>
										<span class="label">{{comzanChange(item)?'已赞':'赞'}}</span>
										<span class="num">({{item.thumbsupnum}})</span>
									</div>
									<div v-if="!comzanChange(item)" class="cai-item" :class="comcaiChange(item)?'active':''" @click="comcaiClick(item)">
										<span class="icon iconfont" :class="comcaiChange(item)?'icon-cai16':'icon-cai01'"></span>
										<span class="label">{{comcaiChange(item)?'已踩':'踩'}}</span>
										<span class="num">({{item.crazilynum}})</span>
									</div>
								</div>
								<div class="comment-btn">
									<!-- <el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px","color":"#fff","borderRadius":"2px","background":"#27a9ab","width":"auto","lineHeight":"32px","fontSize":"14px","minWidth":"80px","height":"32px"}'>回复</el-button> -->
									<el-button class="delBtn" v-if="showIndex==item.id&&userid==item.userid" @click="discussDel(item.id)">删除</el-button>
								</div>
							</div>
							<div class="comment-content-box" v-if="item.reply">
								回复：<span v-html="item.reply"></span>
							</div>
						</div>
					</div>
				
					<el-pagination
						background
						id="pagination" class="pagination"
						:pager-count="7"
						:page-size="pageSize"
						prev-text="上一页"
						next-text="下一页"
						:hide-on-single-page="false"
						:layout='["total","prev","pager","next","sizes","jumper"].join()'
						:total="total"
						@current-change="curChange"
						@prev-click="prevClick"
						@next-click="nextClick"
						></el-pagination>
				</el-tab-pane>
			</el-tabs>

			<div class="idea1"></div>
			<div class="idea2"></div>
		</div>
		<div class="share_view">
		</div>
		<el-dialog title="选择日期" :visible.sync="riqiVisible" width="40%">
			<el-form :model="yuyueForm" label-width="90px">
				<el-row>
					<el-col :span="24">
						<el-form-item label="预约日期">
							<el-date-picker style="width: 100%;" v-model="yuyueForm.reservationdate" type="date" @change="reservationdateChange"
								value-format="yyyy-MM-dd" :picker-options="pickerOptions" format="yyyy-MM-dd"
								placeholder="选择预约日期">
							</el-date-picker>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="时间段">
							<el-time-select style="flex: 1;width: 49%;margin: 0 2% 0 0;" placeholder="开始时间" @change="starttimeChange" v-model="yuyueForm.starttime" :picker-options="{
								  start: starttime,
								  step: '01:00',
								  end: endtime1
								}">
							</el-time-select>
							<el-time-select style="flex: 1;width: 49%;" disabled placeholder="结束时间(自动预约一小时)" v-model="yuyueForm.endtime" :picker-options="{
								  start: yuyueForm.starttime,
								  step: '01:00',
								  end: endtime
								}">
							</el-time-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item>
					<el-button type="primary" @click="xuanzuoClick">选座</el-button>
					<el-button type="danger" @click="riqiVisible=false">取消</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
		<el-dialog title="选择位置" :visible.sync="xuanzuoVisible" width="60%">
			<div style="width: 100%;padding: 10px ;overflow-y:auto;margin: 0 0 60px;max-height: 50vh;"
				v-if="detail.totals>0">
				<div :class="(index%2!=0)&&(detail.rows!=Number(index) + 1)?'hangshu':''"
					v-for="(item,index) in detail.rows" :key="index" style="display: flex;">
					<div @click="xuanzhongClick(Number(indexs) + 1)" v-for="(items,indexs) in detail.totals"
						style="padding: 15px 0;text-align: center;font-size: 14px;"
						:style="{'width':`calc(100% / ${detail.columns})`}"
						v-if="(index==0&&indexs<detail.columns)||(index>0&&(indexs<(detail.columns * (Number(index) + 1)))&&(indexs>=detail.columns * (Number(index))))">
						<img v-if="!yixuanChange(Number(indexs) + 1)" src="@/assets/selected.png" style="width: 40px;"
							alt="">
						<img v-else-if="yixuanChange(Number(indexs) + 1)&&yuyueForm.seatnum!=(Number(indexs) + 1)"
							src="@/assets/unselect.png" style="width: 40px;" alt="">
						<img v-else src="@/assets/select.png" style="width: 40px;" alt="">
						<div>{{Number(indexs) + 1}}号</div>
					</div>
				</div>
			</div>
			<el-button type="primary" @click="submitClick">提交</el-button>
			<el-button type="danger" @click="upClick">上一步</el-button>
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'chongwufuwu',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '宠物服务'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				activeName: 'second',
				form: {
					content: '',
					userid: localStorage.getItem('frontUserid'),
					nickname: localStorage.getItem('username'),
					avatarurl: '',
				},
				showIndex: -1,
				infoList: [],
				rules: {
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					],
				},
				total: 1,
				pageSize: 5,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'chongwufuwu',
					userid: localStorage.getItem('frontUserid')
				},
				isStoreup: false,
				storeupInfo: {},
				buynumber: 1,
				centerType: false,
				shareUrl: location.href,
				riqiVisible: false,
				yuyueForm: {
					reservationdate: '',
					timeslot: '',
					seatnum: '',
					starttime: '',
					endtime: ''
				},
				xuanzuoVisible: false,
				pickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				yixuanzuowei: '',
				starttime: '',
				starttime1: '',
				endtime: '',
				endtime1: '',
				yuyuetable: '',
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			
			this.init();
		},
		mounted() {
		},
		//方法集合
		methods: {
			reservationdateChange(e) {
				if (new Date(e).getTime() == new Date(this.getCurDate()).getTime()) {
					let time = this.getNextHour()
					var hour = time.getHours()
					this.starttime = hour + ':00'
				}else{
					this.starttime = this.starttime1
				}
			},
			getNextHour() {
				var now = new Date();
				var minutes = now.getMinutes(); // 获取当前的分钟数
				var seconds = now.getSeconds(); // 获取当前的秒数
				// 将当前时间调整到下一个整点
				now.setMinutes(minutes + (60 - minutes % 60), 0); // 加上差值直到整点
				now.setSeconds(seconds); // 保留当前秒数
				now.setMilliseconds(0); // 清除毫秒数
				return now; // 返回调整后的时间
			},
			yuyueClick(table) {
				this.yuyueForm = {
					reservationdate: '',
					timeslot: '',
					seatnum: '',
					starttime: '',
					endtime: ''
				}
				this.yuyuetable = table
				this.riqiVisible = true
			},
			async xuanzuoClick() {
				if (this.yuyueForm.reservationdate == '') {
					return false
				}
				if (this.yuyueForm.starttime == '') {
					return false
				}
				await this.getYuyue()
				this.riqiVisible = false
				this.xuanzuoVisible = true
			},
			async getYuyue() {
				await this.$http.get(`${this.yuyuetable}/list`, {
					params: {
						refno: this.detail.refno,
						reservationdatestart: this.yuyueForm.reservationdate,
						reservationdateend: this.yuyueForm.reservationdate,
						timeslot: this.yuyueForm.starttime + '-' + this.yuyueForm.endtime
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						let arr = []
						for (let x in res.data.data.list) {
							if(res.data.data.list[x].reservationstate != '已取消'){
								arr.push(res.data.data.list[x].seatnum)
							}
						}
						this.yixuanzuowei = arr
					}
				})
			},
			upClick() {
				this.xuanzuoVisible = false
				this.riqiVisible = true
			},
			starttimeChange(){
				let a = this.yuyueForm.starttime.split(':')[0]
				let b = this.yuyueForm.starttime.split(':')[1]
				let c = Number(a) + 1
				this.yuyueForm.endtime = (c<10?'0':'') + c + ':' + b
				this.$forceUpdate()
			},
			yixuanChange(index) {
				for (let x in this.yixuanzuowei) {
					if (this.yixuanzuowei[x] == index) {
						return false
					}
				}
				return true
			},
			xuanzhongClick(index) {
				if (!this.yixuanChange(index)) {
					this.$message.error('该座位已被预约')
					return false
				}
				this.yuyueForm.seatnum = String(index)
			},
			submitClick() {
				this.yuyueForm = Object.assign(this.detail, this.yuyueForm)
				this.yuyueForm.timeslot = this.yuyueForm.starttime + '-' + this.yuyueForm.endtime
				localStorage.setItem('crossTable', `chongwufuwu`);
				localStorage.setItem('crossObj', JSON.stringify(this.yuyueForm));
				this.$router.push({
					path: `/index/${this.yuyuetable}Add`,
					query: {
						type: 'cross'
					}
				});
			},
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						if (res.data.data.opentime) {
							this.starttime = res.data.data.opentime.split('-')[0]
							this.starttime1 = res.data.data.opentime.split('-')[0]
							this.endtime = res.data.data.opentime.split('-')[1]
							let a = res.data.data.opentime.split('-')[1]
							let b = a.split(':')[1]
							let c = Number(a.split(':')[0]) - 1
							this.endtime1 = (c < 10 ? '0' : '') + c + ':' + b
						}
						this.title = this.detail.fuwumingcheng;
						this.detailBanner = this.detail.fengmian ? this.detail.fengmian.split(",") : [];
						this.$forceUpdate();
						this.getDiscussList(1);
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
						}

					}
				});
			},
			async onAcross(acrossTable,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1){
				localStorage.setItem('crossTable',`chongwufuwu`);
				localStorage.setItem('crossObj', JSON.stringify(this.detail));
				localStorage.setItem('statusColumnName',statusColumnName);
				localStorage.setItem('statusColumnValue',statusColumnValue);
				localStorage.setItem('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								type: 'error',
								message: tips,
								duration: 1500
							});
							return
						}
					}
				}
				this.$router.push({path: '/index/' + acrossTable + 'Add', query: {type: 'cross'}});
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = type;
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('chongwufuwu/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'chongwufuwu', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('chongwufuwu/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'chongwufuwu', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				let params = {}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/chongwufuwu', query: params});
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			getDiscussList(page) {
				this.$http.get('discusschongwufuwu/list', {params: {page, limit: this.pageSize, refid: this.detail.id,sort: 'istop',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.infoList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
					}
				});
			},
			comzanChange(row){
				if(row.tuserids){
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comzanClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.userid
					}else {
						row.tuserids = this.userid
					}
					this.$http.post('discusschongwufuwu/update',row).then(rs=>{
						this.$message.success('点赞成功')
					})
				}else {
					row.thumbsupnum--
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					this.$http.post('discusschongwufuwu/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comcaiClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.userid
					}else {
						row.cuserids = this.userid
					}
					this.$http.post('discusschongwufuwu/update',row).then(rs=>{
						this.$message.success('点踩成功')
					})
				}else {
					row.crazilynum--
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					this.$http.post('discusschongwufuwu/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			discussEnter(index){
				this.showIndex = index
			},
			discussLeave(){
				this.showIndex = -1
			},
			discussDel(id){
				this.$confirm('是否删除此评论？').then(_ => {
					this.$http.post('discusschongwufuwu/delete',[id]).then(res=>{
						if(res.data&&res.data.code==0){
							this.addDiscussNum(1)
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									this.getDiscussList(1);
								}
							});
						}
					})
				}).catch(_ => {});
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.form.refid = this.detail.id;
						this.form.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
						this.$http.post('discusschongwufuwu/add', this.form).then(rs2 => {
							if (rs2.data.code == 0) {
								this.form.content = '';
								this.addDiscussNum(2)
								this.getDiscussList(1);
								this.$message({
									type: 'success',
									message: '评论成功!',
									duration: 1500,
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			addDiscussNum(type){
				if(type==2){
					this.detail.discussnum++
				}else if(type==1){
					if(this.detail.discussnum!=0){
						this.detail.discussnum--
					}else{
						this.detail.discussnum = 0
					}
				}
				this.$http.post('chongwufuwu/update',this.detail).then(res=>{})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/chongwufuwuAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此宠物服务？') .then(_ => {
					this.$http.post('chongwufuwu/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'chongwufuwu',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
						}
					});
				}).catch(_ => {});
			},
		},
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 20px 0;
		margin: 0px auto;
		color: #666;
		display: flex;
		width: 1200px;
		font-size: 16px;
		justify-content: space-between;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0 40px;
			background: #fff;
			display: block;
			width: 100%;
			position: relative;
			order: 3;
			.info {
				padding: 0;
				margin: 0;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				flex-wrap: wrap;
				.title-item {
					border-radius: 0px;
					padding: 10px 10px;
					margin: 0;
					background: #fff;
					display: flex;
					width: 48%;
					border-color: #000;
					border-width: 0 0 1px;
					line-height: 1.5;
					justify-content: space-between;
					align-items: center;
					border-style: dashed;
					.detail-title {
						color: #000;
						font-weight: 500;
						font-size: 16px;
					}
					.colectBtn {
						cursor: pointer;
						padding: 0px;
						background: none;
						.icon {
							color: #333;
							font-size: inherit;
						}
						.text {
							color: #333;
							font-size: inherit;
						}
					}
					.colectBtnActive {
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
					.colectBtn:hover {
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
					.colectBtnActive:hover {
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
				}
				.item {
					border-radius: 0px;
					padding: 8px 0 !important;
					margin: 0 0 3px 0;
					background: #fff !important;
					display: flex;
					width: 48%;
					border-color: #000 !important;
					border-width:  0 0 1px !important;
					justify-content: spaceBetween;
					align-items: center;
					border-style: dashed !important;
					.lable {
						padding: 0 10px;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 24px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 0px 10px 0;
						color: inherit;
						word-break: break-all;
						flex: 1;
						font-size: inherit;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					font-size: 14px;
					flex-wrap: wrap;
				}
				.reserveBtn {
					border: 0px solid rgba(33, 205, 205, .3);
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: rgba(33, 205, 205, 1);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.reserveBtn:hover {
				}
				.editBtn {
					border: 0px solid rgba(64, 158, 255, .3);
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #fff;
					background: rgba(64, 158, 255, 1);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
				}
				.delBtn {
					border: 0px solid rgba(209, 6, 2, .3);
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: rgba(209, 6, 2, 1);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
				}
			}
		}
		.el-carousel {
			margin: 0;
			display: block;
			width: 100%;
			text-align: center;
			height: 480px;
			order: 1;
			/deep/ .el-carousel__indicator button {
				width: 0;
				height: 0;
				display: none;
			}
			/deep/ .el-carousel__container {
				.el-carousel__arrow--left {
					width: 36px;
					font-size: 12px;
					height: 36px;
				}
				.el-carousel__arrow--left:hover {
					background: #1b4955;
				}
				.el-carousel__arrow--right {
					width: 36px;
					font-size: 12px;
					height: 36px;
				}
				.el-carousel__arrow--right:hover {
					background: #1b4955;
				}
				.el-carousel__item {
					border-radius: 0px;
					width: 100%;
					height: 100%;
					img {
						border: 0px solid #eee;
						padding: 20px;
						background: #fff;
						object-fit: contain;
						width: 100%;
						height: 100%;
					}
				}
			}
		
			/deep/ .el-carousel__indicators {
				padding: 0;
				margin: 0;
				z-index: 2;
				position: absolute;
				list-style: none;
				li {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 12px;
					opacity: 0.4;
					transition: 0.3s;
					height: 12px;
				}
				li:hover {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 24px;
					opacity: 0.7;
					height: 12px;
				}
				li.is-active {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 24px;
					opacity: 1;
					height: 12px;
				}
			}
		}
		.detail-tabs {
			border: 0px solid #ccc;
			box-shadow: none;
			padding: 0;
			margin: 20px auto 40px;
			background: #fff;
			width: 100%;
			order: 21;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 5px 20px;
				margin: 0;
				background: #1b4955;
				border-color: #d1060230;
				border-width: 0 0 0px 0;
				border-style: solid;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 20px;
				margin: 0 5px;
				color: #fff;
				font-weight: 500;
				display: inline-block;
				font-size: inherit;
				line-height: 44px;
				transition: all 0s;
				background: none;
				position: relative;
				list-style: none;
				height: 44px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border-radius: 50px;
				color: #1b4955;
				background: #fff;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border-radius: 50px;
				color: #1b4955;
				background: #fff;
				font-size: 16px;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px;
			}
			.commentForm {
				box-shadow: none;
				padding: 0px;
				margin: 20px 0;
				.item {
					padding: 0;
					display: flex;
					width: 100%;
					height: auto;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 40px;
						text-align: left;
					}
					.editor {
						border: 1px solid #1b4955;
						border-radius: 50px;
						padding: 20px;
						color: #333;
						background: #fff;
						width: 100%;
						font-size: 14px;
						min-height: 350px;
						line-height: 32px;
						/deep/ .avatar-uploader {
							height: 0;
							line-height: 0;
						}
					}
				}
				.commentBtn {
					padding: 0 0 0 40px;
					margin: 10px 0 0;
					width: 100%;
					text-align: center;
					height: auto;
					.submitBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 20px 0 25px;
						margin: 0 20px 0 0;
						color: #fff;
						background: #1b4955;
						width: auto;
						font-size: 15px;
						line-height: 48px;
						min-width: 140px;
						height: 48px;
					}
					.submitBtn:hover {
						opacity: 0.8;
					}
					.resetBtn {
						border: 0;
						cursor: pointer;
						border-radius: 2px;
						padding: 0 20px 0 25px;
						margin: 0 20px 0 0;
						color: #fff;
						background: #27a9ab;
						width: auto;
						font-size: 15px;
						line-height: 48px;
						min-width: 140px;
						height: 48px;
					}
					.resetBtn:hover {
						opacity: 0.8;
					}
				}
			}
			.comment-list {
				box-shadow: none;
				padding: 0px;
				margin: 40px 0 0;
				display: flex;
				justify-content: space-between;
				flex-wrap: wrap;
				.comment-item {
					border-radius: 50px;
					padding: 20px;
					margin: 0 0 20px;
					width: 100%;
					border-color: #1b4955;
					border-width: 1px;
					align-items: center;
					border-style: solid;
					height: auto;
					.istop {
						box-shadow: 0 4px 8px rgba(0,0,0,.1);
						top: 0;
						background: #fff;
						position: absolute;
						right: 10px;
						.icon {
							color: #000;
						}
					}
					.user {
						padding: 5px;
						background: none;
						display: flex;
						width: 100%;
						align-items: center;
						height: auto;
						.el-image {
							border-radius: 100%;
							margin: 0 10px 0 0;
							object-fit: cover;
							width: 40px;
							height: 40px;
						}
						.name {
							color: #333;
							font-size: 16px;
						}
					}
					.comment-content-box {
						border-radius: 4px;
						padding: 0px;
						margin: 5px 0px 0px;
						word-wrap: break-word;
						color: #666;
						background: none;
						font-size: 14px;
						line-height: 1.5;
						.zancai-box {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 30px;
							.zan-item {
								margin: 0 10px 0 0;
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.zan-item.active {
								margin: 0 10px 0 0;
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.zan-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff0000;
								}
								.label {
								color: #ff0000;
								}
								.num {
								color: #ff0000;
								}
							}
							.cai-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.cai-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.cai-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff0000;
								}
								.label {
								color: #ff0000;
								}
								.num {
								color: #ff0000;
								}
							}
						}
						.comment-btn {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 40px;
							.delBtn {
								border: 0;
								cursor: pointer;
								padding: 0 20px;
								margin: 0 10px;
								color: #fff;
								font-size: 14px;
								line-height: 32px;
								border-radius: 2px;
								outline: none;
								background: #1b4955;
								width: auto;
								min-width: 80px;
								height: 32px;
							}
						}
					}
				}
			}
		}
	}
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 0;
		.share:last-of-type{
			border:none;
		}
	}
	.idea1 {
		background: none;
		width: 100%;
		height: 1px;
		order: 4;
	}
	.idea2 {
		background: #fff;
		width: 100%;
		height: 1px;
		order: 6;
	}
</style>
