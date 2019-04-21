define([ 'app', 'route','$httpRequest','$ctx','$jBoxcm' ], function(app) {
	app.run([ '$rootScope', '$transitions','$httpRequest','$ctx','$jBoxcm', function($rootScope, $transitions,$httpRequest,$ctx,$jBoxcm) {
		$transitions.onSuccess({}, function(trans) {
			$(document).ready(function() {
				$rootScope.city = 10;
				$("select.select2").each(function() {
					$(this).select2();
				});
				$httpRequest.post($ctx.getWebPath() + "common/enums").then(function(res) {
			                  if(res.success){
			                	  $ctx.initEnums($rootScope,res.data);
			                  } else {
			                	  $jBoxcm.error("获取枚举失败,"+res.errorMessage);
			                  }
				});
				$httpRequest.post($ctx.getWebPath() + "common/namespace").then(function(res) {
	                  if(res.success){
	                	  $ctx.initNamespace($rootScope,res.data);
	                  } else {
	                	  $jBoxcm.error("获取命名空间失败,"+res.errorMessage);
	                  }
				});
			});
		});
	} ]);

});