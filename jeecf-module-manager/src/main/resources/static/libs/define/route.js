/**
 * 配置路由
 */
define([ 'app' ], function(app) {
	app.config(function($provide){
		   $provide.decorator('$httpRequest', function($delegate){
		      return $delegate;
		  });
	}).config([ '$stateProvider', '$urlRouterProvider','$controllerProvider','$httpRequestProvider',
		function($stateProvider, $urlRouterProvider,$controllerProvider,$httpRequestProvider) {
		    var $httpRequest = $httpRequestProvider.$get();
		    $httpRequest.ajax("route",{},{ 
				success : function(res){
					 var data = res.data;
					 $urlRouterProvider.when('',"/doc");
				     for(var i in data){
				    	 if(data[i].level == 1 ){
				    		 var newData = getFirstRoute(data[i].id,data);
				    		 if(newData != undefined){
				    			 setSate({"routeName":data[i].routeName,"label":newData.label,"moduleLabel":newData.moduleLabel});
				    		 }
						 }
				    	 if(data[i].level != 1 && data[i].route == 1){
				    		 setSate({"routeName":data[i].routeName,"label":data[i].label,"moduleLabel":data[i].moduleLabel});
				    	 }
				     }
				 }
			});
			
			function requireModule(path, controllerName) {
	                return function ($q) {
	                    var deferred = $q.defer();
	                    require([path], function (controller) {
	                        $controllerProvider.register(controllerName, controller);
	                        deferred.resolve();
	                    });
	                    return deferred.promise;
	                }
	       }
		    
		   function getFirstRoute(id,data){
			   for(var j in data){
				   if(data[j].parentId != null && data[j].parentId == id){
					   if(data[j].route == 1){
						   return data[j];
					   } else {
						   return getFirstRoute(data[j].id,data);
					   }
				   }
			   }
		   }
		   
		   function setSate(ops) {
			    var url = "/" + ops.moduleLabel + "/" + ops.label;
			    if(ops.routeName.split("@").length == 1){
				    url = "/" + ops.moduleLabel;
			    } 
			    var templateUrl = "/" + ops.moduleLabel + "/" + ops.label;
		   		var controller = ops.label+ "Controller";
		   		var labelAs = ops.label+ "As";
		   		var loadPath = "controller/" +  ops.moduleLabel + "/" +controller;
		   		$stateProvider.state(ops.routeName, {
		   			url : url,
		   			resolve: {
		   				load: requireModule(loadPath,controller)
		   			},
		   			views : {
//		   				"siderbar" : {
//		   					templateUrl : ops.moduleLabel
//		   				},
		   				"content" : {
		   					templateUrl : templateUrl,
		   					controller : controller,
		   					controllerAs: labelAs
		   				}
		   			}
		   		});
		   }

	} ]);
			

})