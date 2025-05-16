<template>
	<div>

		<div class="container">
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='security1'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="username">
						<div class="label">账号：</div>
						<el-input v-model="forgetForm.username" placeholder="请您输入账号" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="getSecurity()">下一步</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='security2'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="pquestion">
						<div class="label">密保问题：</div>
						<el-input readonly v-model="registerForm.pquestion" placeholder="密保问题" />
					</el-form-item>
					<el-form-item class="list-item" prop="myanswer">
						<div class="label">密保答案：</div>
						<el-input v-model="registerForm.myanswer" placeholder="请您输入密保答案" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="validateSecurity()">下一步</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='security3'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="newpassword">
						<div class="label">新密码：</div>
						<el-input v-model="registerForm.newpassword" type="password" placeholder="请您输入新密码" />
					</el-form-item>
					<el-form-item class="list-item" prop="repassword">
						<div class="label">确认密码：</div>
						<el-input v-model="registerForm.repassword" type="password" placeholder="请您确认密码" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="updatePassword()">修改密码</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">爱猫小当家</p></div>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="zhanghao">
						<div class="label" :class="changeRules('zhanghao')?'required':''">账号：</div>
						<el-input v-model="registerForm.zhanghao"  placeholder="请输入账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="xingming">
						<div class="label" :class="changeRules('xingming')?'required':''">姓名：</div>
						<el-input v-model="registerForm.xingming"  placeholder="请输入姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in yonghuxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="shouji">
						<div class="label" :class="changeRules('shouji')?'required':''">手机：</div>
						<el-input v-model="registerForm.shouji"  placeholder="请输入手机" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="youxiang">
						<div class="label" :class="changeRules('youxiang')?'required':''">邮箱：</div>
						<el-input v-model="registerForm.youxiang"  placeholder="请输入邮箱" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="shenfenzheng">
						<div class="label" :class="changeRules('shenfenzheng')?'required':''">身份证：</div>
						<el-input v-model="registerForm.shenfenzheng"  placeholder="请输入身份证" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="yonghutouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="pquestion">
						<div class="label" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input v-model="registerForm.pquestion"  placeholder="请输入密保问题" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="panswer">
						<div class="label" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input v-model="registerForm.panswer"  placeholder="请输入密保答案" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			requiredRules: {},
            yonghuxingbieOptions: [],
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='yonghu'){
				this.registerForm = {
					zhanghao: '',
					mima: '',
					mima2: '',
					xingming: '',
					xingbie: '',
					shouji: '',
					youxiang: '',
					shenfenzheng: '',
					touxiang: '',
					jf: '',
					money: '',
					pquestion: '',
					panswer: '',
				}
			}
			if ('yonghu' == this.tableName) {
				this.rules.zhanghao = [{ required: true, message: '请输入账号', trigger: 'blur' }];
				this.requiredRules.zhanghao = [{ required: true, message: '请输入账号', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.yonghuxingbieOptions = "男,女".split(',');
			if ('yonghu' == this.tableName) {
				this.rules.shouji = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			if ('yonghu' == this.tableName) {
				this.rules.jf = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
			if ('yonghu' == this.tableName) {
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
			if(`yonghu` == this.tableName){
				this.rules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }];
				this.requiredRules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }]
			}
			if(`yonghu` == this.tableName){
				this.rules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }];
				this.requiredRules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }]
			}
		}
	},
    created() {
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		yonghutouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


//获取密保问题
		getSecurity(){
			this.tableName = 'yonghu'
			if(!this.tableName) {
				this.$message.error("请选择角色");
				return false
			}
			if(!this.forgetForm.username){
				this.$message.error("请输入账号");
				return false
			}
			this.$http({
				url: this.tableName+`/security?username=` + this.forgetForm.username,
				method: "get",
			}).then(({
				data
			}) => {
				if (data.data && data.code === 0) {
					this.registerForm = data.data;
					this.pageFlag="security2"
				} else {
					if(!data.data) {
						this.$message.error("用户不存在");
					} else {
						this.$message.error(data.msg);
					}
				}
			});
		},
		//验证密保问题
		validateSecurity(){
			let that = this
			if(this.registerForm.panswer!=this.registerForm.myanswer){
				this.$message.error("答案输入不正确");
				return false
			}
			this.$message.success("答案正确");
			setTimeout(()=>{
				this.pageFlag="security3";
			},1000)
		},
		// 修改密码
		updatePassword() {
			if (!this.registerForm.newpassword) {
				this.$message.error("请输入新密码");
				return;
			}
			if (this.registerForm.newpassword != this.registerForm.repassword) {
				this.$message.error("两次密码输入不一致");
				return;
			}
			this.registerForm.mima = this.registerForm.newpassword;
			this.registerForm.password = this.registerForm.newpassword;
			this.$http.post(this.tableName+`/update`, this.registerForm).then(res => {
			  if (res.data.code === 0) {
				this.$message({
					message: "密码修改成功",
					type: "success",
					duration: 1500,
					onClose: () => {
					  this.$router.push('/login');
					}
				});
			  } else {
				this.$message.error(res.data.msg);
			  }
			});
		},
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(`yonghu` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					this.$http.post(url, this.registerForm).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background-repeat: no-repeat;
		background-size: 100% 100% !important;
		background: url(http://codegen.caihongy.cn/20241202/e6375f2dc4bd402a8ff0d5bac37717f2.jpg);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20241202/e6375f2dc4bd402a8ff0d5bac37717f2.jpg);
		.rgs-form {
			border-radius: 10px;
			padding: 20px 60px 20px 20px;
			margin: 0;
			z-index: 10;
			background: #fff;
			width: 600px;
			position: relative;
			height: auto;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 0 auto 20px;
					color: #333;
					font-weight: 600;
					width: 100%;
					font-size: 16px;
					line-height: 44px;
					text-align: center;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					margin: 0 auto 20px;
					width: 100%;
					position: relative;
					/deep/.el-form-item__content {
						padding: 0 0 0 120px;
						display: block;
						width: calc(100% - 0px);
						.label {
							padding: 0 5px 0 0;
							z-index: 9;
							color: #333;
							left: 0;
							width: 120px;
							font-size: 15px;
							line-height: 40px;
							position: absolute !important;
							text-align: right;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							margin: 0 10px 0 0;
							color: red;
							left: 110px;
							position: inherit;
							content: "*";
						}
						.el-input {
							flex: 1;
							width: 100%;
						}
						.el-input .el-input__inner {
							border: 0px solid #ebd8ba;
							border-radius: 4px;
							padding: 0 10px;
							color: #666;
							flex: 1;
							background: #dce2e5;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-input .el-input__inner:focus {
							border: 0px solid #96a7c9;
							border-radius: 4px;
							padding: 0 10px;
							outline: none;
							color: #666;
							background: #dce2e5;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-select {
							flex: 1;
							width: calc(100% - 0px);
						}
						.el-select .el-input__inner {
							border: 0px solid #ebd8ba;
							border-radius: 4px;
							padding: 0 10px;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-select .el-input__inner:focus {
							border: 0px solid #96a7c9;
							border-radius: 4px;
							padding: 0 10px;
							outline: none;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-date-editor {
							flex: 1;
							width: calc(100% - 0px);
						}
						.el-date-editor .el-input__inner {
							border: 0px solid #ebd8ba;
							border-radius: 4px;
							padding: 0 10px 0 30px;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-date-editor .el-input__inner:focus {
							border: 0px solid #96a7c9;
							border-radius: 4px;
							padding: 0 10px 0 30px;
							outline: none;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 15px;
							height: 40px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px solid #dce2e5;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: #dce2e5;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px solid #dce2e5;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: #dce2e5;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px solid #dce2e5;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: #dce2e5;
							width: 80px;
							font-size: 24px;
							line-height: 50px;
							text-align: center;
							height: 50px;
						}
						.el-upload__tip {
							color: #666;
							font-size: 15px;
						}
						.emailInput {
							border: 0px solid #ebd8ba;
							border-radius: 4px;
							padding: 0 10px;
							margin: 0;
							color: #606266;
							background: #dce2e5;
							flex: 1;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.emailInput:focus {
							border: 0px solid #96a7c9;
							border-radius: 4px;
							padding: 0 10px;
							outline: none;
							color: #606266;
							background: #dce2e5;
							width: calc(100% - 0px);
							font-size: 15px;
							height: 40px;
						}
						.el-btn {
							border: 1px solid #1b4955;
							cursor: pointer;
							border-radius: 4px;
							padding: 0 10px;
							margin: 0;
							color: #fff;
							background: #1b4955;
							width: 110px;
							font-size: 15px;
							border-width: 1px 1px 1px 0;
							float: right;
							height: 40px;
						}
						.el-btn:hover {
							opacity: 0.8;
						}
						
						.el-input__inner::placeholder {
							color: #999;
							font-size: 15px;
						}
						input::placeholder {
							color: #999;
							font-size: 15px;
						}
						.editor {
							margin: 0 0 0 0px;
							background: #fff;
							width: calc(100% - 0px);
							height: auto;
						}
					}
				}
				.register-btn {
					margin: 20px auto;
					display: flex;
					width: 100%;
					flex-wrap: wrap;
				}
				.register-btn1 {
					padding: 0 0 0 120px;
					width: 100%;
					order: 2;
				}
				.register-btn2 {
					padding: 0 0 0 120px;
					margin: 0px auto 10px;
					width: 100%;
					text-align: center;
				}
				.register_btn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 0px;
					color: #fff;
					background: #1b4955;
					width: 100%;
					font-size: 18px;
					height: 44px;
				}
				.register_btn:hover {
				}
				.has_btn {
					cursor: pointer;
					padding: 0;
					color: #555;
					display: inline-block;
					text-decoration: none;
					font-size: 15px;
					line-height: 40px;
				}
				.has_btn:hover {
					opacity: 0.8;
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
