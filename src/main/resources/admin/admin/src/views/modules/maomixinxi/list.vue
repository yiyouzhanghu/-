<template>
  <div id="xingbieChart2"></div>
  <div class="main-content" :style='{"width":"100%","padding":"20px 40px","fontSize":"15px"}'>
    <!-- 列表页 -->
    <template v-if="showFlag">
      <el-form class="center-form-pv" :style='{"border":"0px solid #fff","margin":"0 0px 20px","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between"}' :inline="true" :model="searchForm">
        <el-row :style='{"padding":"0px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex"}' >
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">猫咪名称</label>
            <el-input v-model="searchForm.maomimingcheng" placeholder="猫咪名称" @keydown.enter.native="search()" clearable></el-input>
          </div>
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}' class="select" label="性别" prop="xingbie">
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">性别</label>
            <el-select clearable v-model="searchForm.xingbie" placeholder="请选择性别" >
              <el-option v-for="(item,index) in xingbieOptions" v-bind:key="index" :label="item" :value="item"></el-option>
            </el-select>
          </div>
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">年龄</label>
            <el-input v-model="searchForm.nianling" placeholder="年龄" @keydown.enter.native="search()" clearable></el-input>
          </div>
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}' class="select" label="状态" prop="zhuangtai">
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">状态</label>
            <el-select clearable v-model="searchForm.zhuangtai" placeholder="请选择状态" >
              <el-option v-for="(item,index) in zhuangtaiOptions" v-bind:key="index" :label="item" :value="item"></el-option>
            </el-select>
          </div>
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}' class="select" label="健康状况" prop="jiankangzhuangkuang">
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">健康状况</label>
            <el-select clearable v-model="searchForm.jiankangzhuangkuang" placeholder="请选择健康状况" >
              <el-option v-for="(item,index) in jiankangzhuangkuangOptions" v-bind:key="index" :label="item" :value="item"></el-option>
            </el-select>
          </div>
          <div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">性格特点</label>
            <el-input v-model="searchForm.xinggetedian" placeholder="性格特点" @keydown.enter.native="search()" clearable></el-input>
          </div>
          <el-button class="search" type="success" @click="search()">
            <span class="icon iconfont icon-fangdajing02" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
            查询
          </el-button>
        </el-row>

        <el-row class="actions" :style='{"padding":"0px","margin":"0px 0","flexWrap":"wrap","background":"none","display":"flex"}'>
          <el-button class="add" v-if="isAuth('maomixinxi','新增')" type="success" @click="addOrUpdateHandler()">
            <span class="icon iconfont icon-tianjia13" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
            添加
          </el-button>
          <el-button class="del" v-if="isAuth('maomixinxi','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
            <span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
            删除
          </el-button>


          <el-button class="btn18" v-if="isAuth('maomixinxi','猫类种类')" type="success" @click="chartDialog1()">
            <span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#939393","height":"34px"}'></span>
            猫类种类
          </el-button>
          <el-button class="btn18" v-if="isAuth('maomixinxi','猫咪数量')" type="success" @click="chartDialog2()">
            <span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#939393","height":"34px"}'></span>
            猫咪数量
          </el-button>
          <el-button class="btn18" v-if="isAuth('maomixinxi','猫咪状态')" type="success" @click="chartDialog3()">
            <span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#939393","height":"34px"}'></span>
            猫咪状态
          </el-button>
        </el-row>
      </el-form>
      <div :style='{"border":"0px solid #fff","padding":"0","color":"#000","borderRadius":"10px","background":"#fff","borderWidth":"0px","width":"100%","fontSize":"14px"}'>
        <el-table class="tables"
                  :stripe='false'
                  :style='{"padding":"0px 0","borderColor":"#f6f6f6","borderRadius":"0","borderWidth":"0px 0 0 0px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}'
                  :border='true'
                  v-if="isAuth('maomixinxi','查看')"
                  :data="dataList"
                  v-loading="dataListLoading"
                  @selection-change="selectionChangeHandler">
          <el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
          <el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
          <el-table-column :resizable='true' :sortable='true'
                           prop="maomimingcheng"
                           label="猫咪名称">
            <template slot-scope="scope">
              {{scope.row.maomimingcheng}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="maomizhonglei"
                           label="猫咪种类">
            <template slot-scope="scope">
              {{scope.row.maomizhonglei}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="xingbie"
                           label="性别">
            <template slot-scope="scope">
              {{scope.row.xingbie}}
            </template>
          </el-table-column>
          <el-table-column  :resizable='true' prop="fengmian" width="200" label="封面">
            <template slot-scope="scope">
              <div v-if="scope.row.fengmian">
                <img v-if="scope.row.fengmian.substring(0,4)=='http'" :src="scope.row.fengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.fengmian.split(',')[0])">
                <img v-else :src="$base.url+scope.row.fengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.fengmian.split(',')[0])">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="nianling"
                           label="年龄">
            <template slot-scope="scope">
              {{scope.row.nianling}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="tizhong"
                           label="体重">
            <template slot-scope="scope">
              {{scope.row.tizhong}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="zhuangtai"
                           label="状态">
            <template slot-scope="scope">
              {{scope.row.zhuangtai}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="shifoujueyu"
                           label="是否绝育">
            <template slot-scope="scope">
              {{scope.row.shifoujueyu}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="jiankangzhuangkuang"
                           label="健康状况">
            <template slot-scope="scope">
              {{scope.row.jiankangzhuangkuang}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="faburiqi"
                           label="发布日期">
            <template slot-scope="scope">
              {{scope.row.faburiqi}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="clicknum"
                           label="点击次数">
            <template slot-scope="scope">
              {{scope.row.clicknum}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="discussnum"
                           label="评论数">
            <template slot-scope="scope">
              {{scope.row.discussnum}}
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true'
                           prop="storeupnum"
                           label="收藏数">
            <template slot-scope="scope">
              {{scope.row.storeupnum}}
            </template>
          </el-table-column>
          <el-table-column width="300" label="操作">
            <template slot-scope="scope">
              <el-button class="view" v-if=" isAuth('maomixinxi','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
                <span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"12px","color":"#fff","display":"none","height":"40px"}'></span>
                查看
              </el-button>
              <el-button class="btn8" v-if="isAuth('maomixinxi','领养申请')" @click="lingyangxinxiCrossAddOrUpdateHandler(scope.row,'cross','','','zhuangtai','已领养','已领养,未领养'.split(',')[0])" type="success">
                <span class="icon iconfont icon-xihuan" :style='{"margin":"0 0px","fontSize":"14px","color":"#939393","display":"none","height":"40px"}'></span>
                领养申请
              </el-button>
              <el-button class="edit" v-if=" isAuth('maomixinxi','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
                <span class="icon iconfont icon-xiugai17" :style='{"margin":"0 0px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
                修改
              </el-button>

              <el-button class="view" v-if="isAuth('maomixinxi','查看评论')" type="success" @click="disscussListHandler(scope.row.id)">
                <span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"12px","color":"#fff","display":"none","height":"40px"}'></span>
                查看评论
              </el-button>



              <el-button class="del" v-if="isAuth('maomixinxi','删除') " type="primary" @click="deleteHandler(scope.row.id )">
                <span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          background
          :page-sizes="[10, 50, 100, 200]"
          :page-size="pageSize"
          :layout="layouts.join()"
          :total="totalPage"
          prev-text="< "
          next-text="> "
          :hide-on-single-page="false"
          :style='{"padding":"10px 20px","boxShadow":"1px 2px 4px #ddd","margin":"20px 0","whiteSpace":"nowrap","color":"#333","textAlign":"left","display":"inline-block","borderRadius":"10px","background":"#fff","width":"auto","fontSize":"inherit","position":"relative","fontWeight":"500"}'
      ></el-pagination>
    </template>

    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

    <lingyangxinxi-cross-add-or-update v-if="lingyangxinxiCrossAddOrUpdateFlag" :parent="this" ref="lingyangxinxiCrossaddOrUpdate"></lingyangxinxi-cross-add-or-update>



    <el-dialog
        :visible.sync="chartVisiable1"
        width="800">
      <div id="maomizhongleiChart1" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog1">返回</el-button>
			</span>
    </el-dialog>
    <el-dialog
        :visible.sync="chartVisiable2"
        width="800">
      <div id="xingbieChart2" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog2">返回</el-button>
			</span>
    </el-dialog>
    <el-dialog
        :visible.sync="chartVisiable3"
        width="800">
      <div id="zhuangtaiChart3" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog3">返回</el-button>
			</span>
    </el-dialog>

    <el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
      <img :src="previewImg" alt="" style="width: 100%;">
    </el-dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import chinaJson from "@/components/echarts/china.json";
import axios from 'axios';
import AddOrUpdate from "./add-or-update";
import lingyangxinxiCrossAddOrUpdate from "../lingyangxinxi/add-or-update";
import {
  Loading
} from 'element-ui';
export default {
  data() {
    return {
      indexQueryCondition: '',
      xingbieOptions: [],
      zhuangtaiOptions: [],
      jiankangzhuangkuangOptions: [],
      searchForm: {
        key: ""
      },
      form:{},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      chartVisiable1: false,
      line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#4dc2de","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"emptyCircle","symbolSize":4},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
     // bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#4dc2de","#00ad45","#507afc","#4dc2de","#00ad45","#507afc","#4dc2de","#00ad45"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
      bar: {
        title: {
          text: '猫咪数量',
          left: 'center'
        },
        legend: {
          data: ['猫咪数量']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '猫咪数量',
            type: 'bar',
            data: []
          }
        ],
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        }
      },
      pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#4dc2de","#00ad45","#507afc","#aab9e6","#6ab1c2","#6dc38f","#67dd96","#8fd8e9"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
      funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#4dc2de","#00ad45","#507afc","#aab9e6","#6ab1c2","#6dc38f","#67dd96","#8fd8e9"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
      boardBase: {"funnelNum":8,"lineNum":8,"gaugeNum":8,"barNum":8,"pieNum":8},
      gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#4dc2de","#00ad45","#507afc","#aab9e6","#6ab1c2","#6dc38f","#67dd96","#8fd8e9"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
      chartVisiable2: false,
      chartVisiable3: false,
      addOrUpdateFlag:false,
      lingyangxinxiCrossAddOrUpdateFlag: false,
      layouts: ["total","prev","pager","next","sizes"],
      previewImg: '',
      previewVisible: false,
    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange();
  },
  mounted() {
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  computed: {
    tablename(){
      return this.$storage.get('sessionTable')
    },
  },
  components: {
    AddOrUpdate,
    lingyangxinxiCrossAddOrUpdate,
  },
  methods: {
    imgPreView(url){
      this.previewImg = url
      this.previewVisible = true
    },
    contentStyleChange() {
      this.contentPageStyleChange()
    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      // if(this.contents.pageTotal) arr.push('total')
      // if(this.contents.pageSizes) arr.push('sizes')
      // if(this.contents.pagePrevNext){
      //   arr.push('prev')
      //   if(this.contents.pagePager) arr.push('pager')
      //   arr.push('next')
      // }
      // if(this.contents.pageJumper) arr.push('jumper')
      // this.layouts = arr.join()
      // this.contents.pageEachNum = 10
    },
    lingyangxinxiCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.lingyangxinxiCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj',row);
      this.$storage.set('crossTable','maomixinxi');
      this.$storage.set('statusColumnName',statusColumnName);
      this.$storage.set('statusColumnValue',statusColumnValue);
      this.$storage.set('tips',tips);
      if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
        var obj = this.$storage.getObj('crossObj');
        for (var o in obj){
          if(o==statusColumnName && obj[o]==statusColumnValue){
            this.$message({
              message: tips,
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              }
            });
            this.showFlag = true;
            this.lingyangxinxiCrossAddOrUpdateFlag = false;
            return;
          }
        }
      }
      this.$nextTick(() => {
        this.$refs.lingyangxinxiCrossaddOrUpdate.init(row.id,type);
      });
    },


    // 统计接口
    chartDialog1() {
      this.chartVisiable1 = !this.chartVisiable1;
      this.$nextTick(()=>{
        var maomizhongleiChart1 = echarts.init(document.getElementById("maomizhongleiChart1"),'macarons');
        this.$http({
          url: "maomixinxi/group/maomizhonglei",
          method: "get",
        }).then(({ data }) => {
          if (data && data.code === 0) {
            let res = data.data;
            let xAxis = [];
            let yAxis = [];
            let pArray = []
            for(let i=0;i<res.length;i++){
              if(this.boardBase&&i==this.boardBase.pieNum){
                break;
              }
              xAxis.push(res[i].maomizhonglei);
              yAxis.push(parseFloat((res[i].total)));
              pArray.push({
                value: parseFloat((res[i].total)),
                name: res[i].maomizhonglei
              })
            }
            var option = {};
            let titleObj = this.pie.title
            titleObj.text = '猫类种类'

            const legendObj = this.pie.legend
            let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
            tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})

            let seriesObj = {
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: pArray,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
            seriesObj = Object.assign(seriesObj , this.pie.series)
            const gridObj = this.pie.grid
            option = {
              backgroundColor: this.pie.backgroundColor,
              color: this.pie.color,
              title: titleObj,
              legend: legendObj,
              grid: gridObj,
              tooltip: tooltipObj,
              series: [seriesObj]
            };
            // 使用刚指定的配置项和数据显示图表。
            maomizhongleiChart1.setOption(option);
            //根据窗口的大小变动图表
            window.onresize = function() {
              maomizhongleiChart1.resize();
            };
          }else{
            this.$message({
              message: data.msg,
              type: "warning",
              duration: 1500,
            })
          }
        });
      })
    },

    // 统计接口
    chartDialog2() {
      this.chartVisiable2 = !this.chartVisiable2;
      this.$nextTick(() => {
        var xingbieChart2 = echarts.init(document.getElementById("xingbieChart2"), 'macarons');
        this.$http({
          url: "maomixinxi/group/xingbie",
          method: "get"
        }).then(({ data }) => {
          if (data && data.code === 0) {
            let res = data.data;
            let xAxis = [];
            let yAxis = [];
            for (let i = 0; i < res.length; i++) {
              if (this.boardBase && i == this.boardBase.barNum) {
                break;
              }
              xAxis.push(res[i].xingbie);
              yAxis.push(parseFloat(res[i].total));
            }
            let option = {
              backgroundColor: this.bar.backgroundColor,
              color: this.bar.color,
              title: this.bar.title,
              legend: this.bar.legend,
              tooltip: this.bar.tooltip,
              xAxis: {
                type: 'category',
                data: xAxis
              },
              yAxis: {
                type: 'value'
              },
              series: [
                {
                  name: '猫咪数量',
                  type: 'bar',
                  data: yAxis
                }
              ],
              grid: this.bar.grid
            };
            // 使用刚指定的配置项和数据显示图表。
            xingbieChart2.setOption(option);
            // 根据窗口的大小变动图表
            window.onresize = function () {
              xingbieChart2.resize();
            };
          } else {
            this.$message({
              message: data.msg,
              type: "warning",
              duration: 1500
            });
          }
        });
      });
    },

    // 统计接口
    chartDialog3() {
      this.chartVisiable3 = !this.chartVisiable3;
      this.$nextTick(()=>{

        var zhuangtaiChart3 = echarts.init(document.getElementById("zhuangtaiChart3"),'macarons');
        this.$http({
          url: "maomixinxi/group/zhuangtai",
          method: "get",
        }).then(({ data }) => {
          if (data && data.code === 0) {
            let res = data.data;
            let xAxis = [];
            let yAxis = [];
            let pArray = []
            for(let i=0;i<res.length;i++){
              if(this.boardBase&&i==this.boardBase.lineNum){
                break;
              }
              xAxis.push(res[i].zhuangtai);
              yAxis.push(parseFloat((res[i].total)));
              pArray.push({
                value: parseFloat((res[i].total)),
                name: res[i].zhuangtai
              })
            }
            var option = {};
            let titleObj = this.line.title
            titleObj.text = '猫咪状态'

            const legendObj = this.line.legend
            let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
            tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})

            let xAxisObj = this.line.xAxis
            xAxisObj.type = 'category'
            xAxisObj.boundaryGap = false
            xAxisObj.data = xAxis

            let yAxisObj = this.line.yAxis
            yAxisObj.type = 'value'

            let seriesObj = {
              data: yAxis,
              type: 'line',
              areaStyle: {}
            }
            seriesObj = Object.assign(seriesObj , this.line.series)
            const gridObj = this.line.grid
            option = {
              backgroundColor: this.line.backgroundColor,
              color: this.line.color,
              title: titleObj,
              legend: legendObj,
              tooltip: tooltipObj,
              xAxis: xAxisObj,
              yAxis: yAxisObj,
              series: [seriesObj],
              grid: gridObj
            };
            // 使用刚指定的配置项和数据显示图表。
            zhuangtaiChart3.setOption(option);
            //根据窗口的大小变动图表
            window.onresize = function() {
              zhuangtaiChart3.resize();
            };
          }else{
            this.$message({
              message: data.msg,
              type: "warning",
              duration: 1500,
            })
          }
        });
      })
    },

    init () {
      this.xingbieOptions = "公,母".split(',')
      this.zhuangtaiOptions = "已领养,未领养".split(',')
      this.jiankangzhuangkuangOptions = "健康,异常".split(',')
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
      }
      if(this.searchForm.maomimingcheng!='' && this.searchForm.maomimingcheng!=undefined){
        params['maomimingcheng'] = '%' + this.searchForm.maomimingcheng + '%'
      }
      if(this.searchForm.maomizhonglei!='' && this.searchForm.maomizhonglei!=undefined){
        params['maomizhonglei'] = this.searchForm.maomizhonglei
      }
      if(this.searchForm.xingbie!='' && this.searchForm.xingbie!=undefined){
        params['xingbie'] = this.searchForm.xingbie
      }
      if(this.searchForm.nianling!='' && this.searchForm.nianling!=undefined){
        params['nianling'] = '%' + this.searchForm.nianling + '%'
      }
      if(this.searchForm.zhuangtai!='' && this.searchForm.zhuangtai!=undefined){
        params['zhuangtai'] = this.searchForm.zhuangtai
      }
      if(this.searchForm.shifoujueyu!='' && this.searchForm.shifoujueyu!=undefined){
        params['shifoujueyu'] = this.searchForm.shifoujueyu
      }
      if(this.searchForm.jiankangzhuangkuang!='' && this.searchForm.jiankangzhuangkuang!=undefined){
        params['jiankangzhuangkuang'] = this.searchForm.jiankangzhuangkuang
      }
      if(this.searchForm.xinggetedian!='' && this.searchForm.xinggetedian!=undefined){
        params['xinggetedian'] = '%' + this.searchForm.xinggetedian + '%'
      }
      let user = JSON.parse(this.$storage.getObj('userForm'))
      this.$http({
        url: "maomixinxi/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    disscussListHandler(id,type) {
      this.$router.push({path:'/discussmaomixinxi',query:{refid:id}});
    },
    // 删除
    async deleteHandler(id ) {
      var ids = id? [Number(id)]: this.dataListSelections.map(item => {
        return Number(item.id);
      });
      await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        await this.$http({
          url: "maomixinxi/delete",
          method: "post",
          data: ids
        }).then(async ({ data }) => {
          if (data && data.code === 0) {
            for(let x in ids){
              await this.$http.get('storeup/list',{params: {
                  page: 1,
                  limit: 100,
                  refid: ids[x],
                  tablename: 'maomixinxi'
                }}).then(async obj=>{
                if(obj.data&&obj.data.code==0){
                  if(obj.data.data.list.length){
                    let arr = []
                    for(let i in obj.data.data.list){
                      arr.push(obj.data.data.list[i].id)
                    }
                    await this.$http.post('storeup/delete',arr).then(()=>{})
                  }
                }
              })
            }
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });

          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },


  }

};
</script>
<style lang="scss" scoped>

.center-form-pv {
  .el-date-editor.el-input {
    width: auto;
  }
}

.el-input {
  width: auto;
}

// form
.center-form-pv .el-input {
  width: auto;
}
.center-form-pv .el-input /deep/ .el-input__inner {
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 0 12px;
  color: #666;
  width: 150px;
  font-size: 15px;
  height: 34px;
}
.center-form-pv .el-select {
  width: auto;
}
.center-form-pv .el-select /deep/ .el-input__inner {
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 0 10px;
  color: #666;
  width: 150px;
  font-size: 15px;
  height: 34px;
}
.center-form-pv .el-date-editor {
  width: auto;
}

.center-form-pv .el-date-editor /deep/ .el-input__inner {
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 0 10px 0 30px;
  color: #666;
  width: 150px;
  font-size: 15px;
  height: 34px;
}

.center-form-pv .search {
  border: 0;
  cursor: pointer;
  border-radius: 20px;
  padding: 0 15px 0 10px;
  color: #fff;
  background: #4dc2de;
  width: auto;
  font-size: 16px;
  height: 34px;
}

.center-form-pv .search:hover {
  opacity: 0.8;
}

.center-form-pv .actions .add {
  border: 0px solid #ddd;
  cursor: pointer;
  border-radius: 20px;
  padding: 0 15px;
  margin: 4px;
  color: #fff;
  background: #4dc2de;
  width: auto;
  font-size: inherit;
  height: 34px;
}

.center-form-pv .actions .add:hover {
  opacity: 0.8;
}

.center-form-pv .actions .del {
  border: 0px solid #ddd;
  cursor: pointer;
  border-radius: 20px;
  padding: 0 15px;
  margin: 4px;
  color: #fff;
  background: #be5353;
  width: auto;
  font-size: inherit;
  height: 34px;
}

.center-form-pv .actions .del:hover {
  opacity: 0.8;
}

.center-form-pv .actions .statis {
  border: 0px solid #ddd;
  cursor: pointer;
  border-radius: 20px;
  padding: 0 15px;
  margin: 4px;
  color: #fff;
  background: #3fc182;
  width: auto;
  font-size: inherit;
  height: 34px;
}

.center-form-pv .actions .statis:hover {
  opacity: 0.8;
}

.center-form-pv .actions .btn18 {
  border: 0px solid #939393;
  cursor: pointer;
  border-radius: 20px;
  padding: 0 15px;
  margin: 4px;
  color: #666;
  background: #fff;
  width: auto;
  font-size: inherit;
  height: 34px;
}

.center-form-pv .actions .btn18:hover {
  opacity: 0.8;
}

// table
.el-table /deep/ .el-table__header-wrapper thead {
  color: #999;
  background: #4dc2de;
  font-weight: 500;
  width: 100%;
}

.el-table /deep/ .el-table__header-wrapper thead tr {
  background: none;
}

.el-table /deep/ .el-table__header-wrapper thead tr th {
  padding: 8px 0;
  background: none;
  border-color: #e3e8e9;
  border-width: 0 4px 0px 0;
  border-style: solid;
  text-align: left;
}

.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
  padding: 0 0 0 5px;
  word-wrap: normal;
  color: #fff;
  white-space: normal;
  font-weight: bold;
  display: flex;
  vertical-align: middle;
  font-size: 14px;
  line-height: 24px;
  text-overflow: ellipsis;
  word-break: break-all;
  width: 100%;
  align-items: center;
  position: relative;
  min-width: 110px;
}


.el-table /deep/ .el-table__body-wrapper tbody {
  width: 100%;
}

.el-table /deep/ .el-table__body-wrapper tbody tr {
  background: #fff;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td {
  padding: 4px 0;
  color: #333;
  background: #fff;
  font-size: inherit;
  border-color: #e3e8e9;
  border-width: 0 4px 0px 0;
  border-style: solid;
  text-align: left;
}


.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
  padding: 4px 0;
  color: #333;
  background: #fcfcfc;
  border-color: #e3e8e9;
  border-width: 0 4px 0px 0;
  border-style: solid;
  text-align: left;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td {
  padding: 4px 0;
  color: #333;
  background: #fff;
  font-size: inherit;
  border-color: #e3e8e9;
  border-width: 0 4px 0px 0;
  border-style: solid;
  text-align: left;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
  padding: 0 0 0 5px;
  overflow: hidden;
  word-break: break-all;
  white-space: normal;
  font-size: inherit;
  line-height: 24px;
  text-overflow: ellipsis;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
  border: 0px solid #157ed2;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  color: #1fc3cb;
  background: url(http://codegen.caihongy.cn/20241006/2199ca2de0444272a5e77cb22da4d081.png) no-repeat center center / 100% 100%;
  width: auto;
  font-size: 14px;
  min-width: 60px;
  height: 36px;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
  opacity: 0.8;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
  border: 0px solid #157ed2;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  color: #1fc3cb;
  background: url(http://codegen.caihongy.cn/20241006/ff34117d34d84517ad3d0a6a7bc7cb41.png) no-repeat center center / 100% 100%;
  width: auto;
  font-size: 14px;
  min-width: 60px;
  height: 36px;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
  opacity: 0.8;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
  border: 0px solid #157ed2;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  color: #1fc3cb;
  background: url(http://codegen.caihongy.cn/20241006/2199ca2de0444272a5e77cb22da4d081.png) no-repeat center center / 100% 100%;
  width: auto;
  font-size: 14px;
  min-width: 60px;
  height: 36px;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
  opacity: 0.8;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
  border: 0px solid #157ed2;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  color: #1fc3cb;
  background: url(http://codegen.caihongy.cn/20241006/ff34117d34d84517ad3d0a6a7bc7cb41.png) no-repeat center center / 100% 100%;
  width: auto;
  font-size: 14px;
  min-width: 60px;
  height: 36px;
}

.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
  opacity: 0.8;
}

// pagination
.main-content .el-pagination /deep/ .el-pagination__total {
  margin: 0 10px 0 0;
  color: #666;
  font-weight: 400;
  display: inline-block;
  vertical-align: top;
  font-size: inherit;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination /deep/ .btn-prev {
  border: none;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #666;
  background: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: normal;
  min-width: 35px;
  height: 28px;
}

.main-content .el-pagination /deep/ .btn-next {
  border: none;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #666;
  background: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: normal;
  min-width: 35px;
  height: 28px;
}

.main-content .el-pagination /deep/ .btn-prev:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #C0C4CC;
  background: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: normal;
  height: 28px;
}

.main-content .el-pagination /deep/ .btn-next:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #C0C4CC;
  background: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: normal;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pager {
  padding: 0;
  margin: 0;
  display: inline-block;
  vertical-align: top;
}

.main-content .el-pagination /deep/ .el-pager .number {
  cursor: pointer;
  border-radius: 2px;
  padding: 0 10px;
  margin: 0 2px;
  color: #666;
  background: none;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: 28px;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pager .number:hover {
  cursor: pointer;
  border-radius: 4px;
  padding: 0 10px;
  margin: 0 2px;
  color: #fff;
  background: #4dc2de;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: 28px;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pager .number.active {
  cursor: default;
  border-radius: 4px;
  padding: 0 10px;
  margin: 0 2px;
  color: #fff;
  background: #4dc2de;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: 28px;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pagination__sizes {
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
  margin: 0 5px;
  width: 100px;
  position: relative;
}

.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
  border: 0px solid #DCDFE6;
  cursor: pointer;
  padding: 0 25px 0 8px;
  color: #606266;
  display: inline-block;
  font-size: 16px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #FFF;
  width: 100%;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
  top: 0;
  position: absolute;
  right: 0;
  height: 100%;
}

.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
  cursor: pointer;
  color: #C0C4CC;
  width: 25px;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
}

.main-content .el-pagination /deep/ .el-pagination__jump {
  margin: 0 0 0 24px;
  color: #606266;
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
  border-radius: 3px;
  padding: 0 2px;
  margin: 0 2px;
  display: inline-block;
  width: 50px;
  font-size: 14px;
  line-height: 18px;
  position: relative;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
  border: 1px solid #DCDFE6;
  cursor: pointer;
  padding: 0 3px;
  color: #606266;
  display: inline-block;
  font-size: 16px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #FFF;
  width: 100%;
  text-align: center;
  height: 28px;
}

// list one
.one .list1-view {
  border: 0;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  outline: none;
  color: #fff;
  background: #157ed2;
  width: auto;
  font-size: 14px;
  height: 32px;
}

.one .list1-view:hover {
  opacity: 0.8;
}

.one .list1-edit {
  border: 0;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  outline: none;
  color: #fff;
  background: #409eff;
  width: auto;
  font-size: 14px;
  height: 32px;
}

.one .list1-edit:hover {
  opacity: 0.8;
}

.one .list1-del {
  border: 0;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 15px;
  margin: 0 5px 5px 0;
  outline: none;
  color: #fff;
  background: rgba(255, 0, 0, 1);
  width: auto;
  font-size: 14px;
  height: 32px;
}

.one .list1-del:hover {
  opacity: 0.8;
}

.one .list1-btn8 {
  border: 0;
  cursor: pointer;
  border-radius: 4px;
  padding: 0 24px;
  margin: 0 5px 5px 0;
  outline: none;
  color: #fff;
  background: rgba(255, 128, 0, 1);
  width: auto;
  font-size: 14px;
  height: 32px;
}

.one .list1-btn8:hover {
  opacity: 0.8;
}

.main-content .el-table .el-switch {
  display: inline-flex;
  vertical-align: middle;
  line-height: 30px;
  position: relative;
  align-items: center;
  height: 30px;
}
.main-content .el-table .el-switch /deep/ .el-switch__label--left {
  cursor: pointer;
  margin: 0 10px 0 0;
  color: #333;
  font-weight: 500;
  display: inline-block;
  vertical-align: middle;
  font-size: 16px;
  transition: .2s;
  height: 30px;
}
.main-content .el-table .el-switch /deep/ .el-switch__label--right {
  cursor: pointer;
  margin: 0 0 0 10px;
  color: #333;
  font-weight: 500;
  display: inline-block;
  vertical-align: middle;
  font-size: 16px;
  transition: .2s;
  height: 30px;
}
.main-content .el-table .el-switch /deep/ .el-switch__core {
  border: 1px solid #75c0d6;
  cursor: pointer;
  border-radius: 15px;
  margin: 0;
  background: #75c0d6;
  display: inline-block;
  width: 42px;
  box-sizing: border-box;
  transition: border-color .3s,background-color .3s;
  height: 20px;
}
.main-content .el-table .el-switch /deep/ .el-switch__core::after {
  border-radius: 100%;
  top: 1px;
  left: 1px;
  background: #fff;
  width: 16px;
  position: absolute;
  transition: all .3s;
  height: 16px;
}
.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
  margin: 0 0 0 -18px;
  left: 100%;
}

.main-content .el-table .el-rate /deep/ .el-rate__item {
  cursor: pointer;
  display: inline-block;
  vertical-align: middle;
  font-size: 0;
  position: relative;
}
.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
  margin: 0 3px;
  display: inline-block;
  font-size: 18px;
  position: relative;
  transition: .3s;
}

</style>
