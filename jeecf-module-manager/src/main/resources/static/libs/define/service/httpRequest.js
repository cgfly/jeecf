define([ 'jquery', 'app' ], function($, app) {
	app.factory('$httpRequest', [ '$http', '$q', function($http, $q) {
		return {
			post : function(url, data) {
				var headers = {
					'Content-Type' : 'application/json'
				};
				var deferred = $q.defer();
				$http({
					method : "POST",
					url : url,
					data : data,
					headers : headers
				}).then(function successCallback(response) {
					deferred.resolve(response.data);
				}, function errorCallback(response) {
					deferred.reject(response);
				})
				return deferred.promise;
			},
			ajax : function(url,data, callback) {
				 $.ajax({
		             type: "POST",
		             url: url,
		             data: data,
		             contentType : "application/json",
		             dataType: "json",
		             async:false,
		             success: function(data){
		            	 callback.success(data);
		             }
		         });
			},
			form : function(action,method){
				  var form = $("<form></form>");
			      form.attr('action',action);
			      form.attr('method',method);
			      form.appendTo("body");
			      form.css('display','none');
			      return form;
			},
			form : function(action,method,params){
				  var form = $("<form></form>");
			      form.attr('action',action);
			      form.attr('method',method);
			      form.appendTo("body");
			      form.css('display','none');
			      for(var key in params){
			    	  console.log(key + " " + params[key]);
			    	  if(params[key] instanceof Array){
			    		  for(var i = 0; i < params[key].length; i++){
			    			  for(key1 in params[key][i]){
			    				  form.append($("<input></input>").attr("type", "hidden").attr("name", key+"[" +i+"]."+key1).attr("value", params[key][i][key1]));
			    			  }
			    		  }
			    	  } else {
				    	  form.append($("<input></input>").attr("type", "hidden").attr("name", key).attr("value", params[key]));
			    	  }
			      }
			      return form;
			},
			upload : function(url,data,callback){ 
				 var deferred = $q.defer();
		         $http({
		              method:'POST',
		              url:url,
		              data: data,
		              headers: {'Content-Type':undefined},
		              		transformRequest: angular.identity 
		              }).then(function successCallback(response) {
							deferred.resolve(response.data);
					  }, function errorCallback(response) {
							deferred.reject(response);
					  })
				 return deferred.promise;
			}
			
		
		}
	} ]);
})