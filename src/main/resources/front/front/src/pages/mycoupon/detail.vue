<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/mycoupon?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
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
						</div>
					</div>
					<div class="item">
						<div class="lable">优惠券id</div>
						<div class="text "  >{{detail.couponid}}</div>
					</div>
					<div class="item">
						<div class="lable">名称</div>
						<div class="text "  >{{detail.name}}</div>
					</div>
					<div class="item">
						<div class="lable">券编号</div>
						<div class="text "  >{{detail.couponnumber}}</div>
					</div>
					<div class="item">
						<div class="lable">满额</div>
						<div class="text "  >{{detail.fullamount}}</div>
					</div>
					<div class="item">
						<div class="lable">优惠额</div>
						<div class="text "  >{{detail.discountamount}}</div>
					</div>
					<div class="item">
						<div class="lable">生效时间</div>
						<div class="text "  >{{detail.startime}}</div>
					</div>
					<div class="item">
						<div class="lable">过期时间</div>
						<div class="text "  >{{detail.endtime}}</div>
					</div>
					<div class="item">
						<div class="lable">优惠券类型</div>
						<div class="text "  >{{detail.type}}</div>
					</div>
					<div class="item">
						<div class="lable">备注</div>
						<div class="text "  >{{detail.remark}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('mycoupon','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('mycoupon','删除')" @click="delClick">删除</el-button>
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
			</el-tabs>

			<div class="idea1"></div>
			<div class="idea2"></div>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'mycoupon',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '我的优惠券'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				activeName: 'first',
				total: 1,
				pageSize: 5,
				totalPage: 1,
				buynumber: 1,
				centerType: false,
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
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
						}

					}
				});
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
				this.$router.push({path: '/index/mycoupon', query: params});
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
				this.$router.push(`/index/mycouponAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此我的优惠券？') .then(_ => {
					this.$http.post('mycoupon/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									history.back()
								}
							});
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
