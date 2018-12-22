define([ 'jquery', 'app' ], function($, app) {
	app.factory('$ctx', function() {
		var ctxPath = $('body').attr('data-ctxPath');
		var wikiAddress = "https://github.com/";
		return {
			getWebPath : function(){
				return ctxPath;
			},
			getWikiUrl : function(uri){
				return wikiAddress+uri;
			}
		}
	});
});