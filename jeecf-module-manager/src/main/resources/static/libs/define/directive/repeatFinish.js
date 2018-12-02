define([ 'app' ], function(app) {
	app.directive('repeatFinish', function() {
		return {
			link : function(scope, element, attr) {
				if (scope.$last == true) {
					setTimeout(function() {
						scope.$eval(attr.repeatFinish);
					}, 1);
				}
			}
		}
	})
});