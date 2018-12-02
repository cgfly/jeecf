define([ 'jquery', 'app' ], function($, app) {
	app.factory('$ctx', function() {
		var ctxPath = $('body').attr('data-ctxPath');
		return {
			getWebPath : function(){
				return ctxPath;
			}
		}
	});
});