define([ 'jquery', 'app' ], function($, app) {
	app.factory('$ctx', function() {
		var ctxPath = $('body').attr('data-ctxPath');
		var wikiAddress = "https://github.com/";
		return {
			getWebPath : function() {
				return ctxPath;
			},
			getWikiUrl : function(uri) {
				return wikiAddress + uri;
			},
			initEnums : function($rootScope, data) {
				for ( var key in data) {
					$rootScope[key] = eval("(" + data[key] + ")");
				}
			},
			getEnum : function($rootScope, name,callback) {
				setTimeout(function() {
					callback($rootScope[name]);
				}, 1000);
			},
			initNamespace : function($rootScope, data) {
				$rootScope["namespaceName"] = data;
			},
			getNamespace : function($rootScope) {
				return $rootScope["namespaceName"];
			}
		}
	});
});