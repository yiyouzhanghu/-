export default {
	baseUrl: 'http://localhost:8080/springbootdj912vwr/',
	name: '/springbootdj912vwr',
	indexNav: [
		{
			name: '猫咪信息',
			url: '/index/maomixinxi',
		},
		{
			name: '宠物商品',
			url: '/index/chongwushangpin',
		},
		{
			name: '宠物服务',
			url: '/index/chongwufuwu',
		},
		{
			name: '公告信息',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '猫咪信息',
			refTable: 'maomizhonglei',
			refColumn: 'maomizhonglei',
		},
		{
			name: '宠物商品',
			refTable: 'shangpinfenlei',
			refColumn: 'shangpinfenlei',
		},
		{
			name: '宠物服务',
			refTable: 'fuwuleixing',
			refColumn: 'fuwuleixing',
		},
	]
}
