define([ 'app' ], function(app) {
	app.directive('watchChange', function() {
		return {
			link : function(scope, element, attrs) {
				scope.$watch(attrs.ngModel, function(nv, ov) {
					if (nv == "") {
						nv = undefined;
					}
					if(ov == ""){
						ov = undefined;
					}
					scope.$eval(attrs.watchChange + "('" + nv + "','" + ov + "')");
				}, true);
			}
		}
	})
});