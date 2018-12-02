require.config({
	baseUrl : "/libs/",
	paths : {
		"jquery" : "jquery/jquery-3.1.1.min",
		"angular" : "angular-1.6.9/angular.min",
		"angular-ui-router" : "angular-1.6.9/angular-ui-router.min",
		"angular-ui-select2" : "angular-1.6.9/angular-ui-select2",
		"bootstrap" : "bootstrap-3.3.7/js/bootstrap.min",
		"select2" : "jquery-select2/3.4/select2.min",
		"treetable" : "jquery-treetable-3.2.0/js/jquery.treetable",
		"jBox" : "jquery-jbox/jBox.min",
		"jBoxNotice" : "jquery-jbox/plugins/Notice/jBox.Notice.min",
		"jBoxConfirm" : "jquery-jbox/plugins/Confirm/jBox.Confirm.min",
		"zTree" : "jquery-zTree/js/jquery.ztree.core.min",
		"zTreeCheck" : "jquery-zTree/js/jquery.ztree.excheck.min",
		

		"app" : "define/app",
		"route" : "define/route",
    	"run" : "define/run",
    	
    	"pagination" : "define/directive/pagination",
    	"selection" : "define/directive/selection",
    	"repeatFinish" : "define/directive/repeatFinish",
    	"watchChange" : "define/directive/watchChange",
    	
    	"$httpRequest" : "define/service/httpRequest",
    	"$page" : "define/service/page",
    	"$ctx" : "define/service/ctx",
    	"$genHelper" : "define/service/genHelper",
    	"$jBoxcm" : "define/service/jBoxcm",
    	"$zTreecm" : "define/service/zTreecm",
    	
    	"controller":"define/controller"
	},
	shim : {
		'angular' : {
			exports : 'angular'
		},
		'angular-select2' : {
			deps : [ "angular" ],
			exports : 'angular-select2'
		},
		'angular-ui-select2':{
			deps : [ "angular" ],
			exports : 'angular-ui-select2'
		},
		'bootstrap' : {
			deps : [ "jquery" ],
			exports : 'bootstrap'
		},	
		'select2' : {
			deps : [ "jquery" ],
			exports : 'select2'
		},
		'zTree' : {
			deps : [ "jquery" ],
			exports : 'zTree'
		},
		'treetable' : {
			deps : [ "jquery" ],
			exports : 'treetable'
		},
		'zTreeCheck':{
			deps : [ "jquery","zTree" ],
			exports : 'zTreeCheck'
		},
		'$zTreecm':{
			deps : [ "jquery","zTreeCheck" ],
			exports : '$zTreecm'
		},
		'jBox' : {
			deps : [ "jquery" ],
			exports : 'jBox'
		},
		'jBoxNotice' : {
			deps : [ "jquery","jBox" ],
			exports : 'jBoxNotice'
		},
		'jBoxConfirm' : {
			deps : [ "jquery","jBox" ],
			exports : 'jBoxConfirm'
		},
		'jBoxcm' : {
			deps : [ "jBox" ],
			exports : 'jBoxcm'
		}
		
	},
	waitSeconds : 0

});

require(['jquery','angular','angular-ui-router','angular-ui-select2','bootstrap','select2','zTree','zTreeCheck','$zTreecm','treetable','jBox','jBoxNotice','jBoxConfirm','app','route','run','pagination','selection','repeatFinish','watchChange','$httpRequest','$page','$ctx','$genHelper','$jBoxcm'],function ($,angular){
	$(document).ready(function () {
	    angular.bootstrap(document,["ithourseManagerApp"]);
	    $(".sidebar-dropdown").removeClass("active");
	   
	    $(".sidebar-dropdown > a").click(function() {
	    	
			$(".sidebar-submenu").slideUp(250);
			if ($(this).parent().hasClass("active")) {
				$(".sidebar-dropdown").removeClass("active");
				$(this).parent().removeClass("active");
			} else {
				$(".sidebar-dropdown").removeClass("active");
				$(this).next(".sidebar-submenu").slideDown(250);
				$(this).parent().addClass("active");
			}

		});

		$("#toggle-sidebar").click(function() {
			$(".page-wrapper").toggleClass("toggled");
		});

//		if (!/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i
//				.test(navigator.userAgent)) {
//			$(".sidebar-content").mCustomScrollbar({
//				axis : "y",
//				autoHideScrollbar : true,
//				scrollInertia : 300
//			});
//			$(".sidebar-content").addClass("desktop");
//
//		}
	});
});
