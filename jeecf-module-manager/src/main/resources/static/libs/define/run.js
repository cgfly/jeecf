define([ 'app', 'route','$httpRequest' ], function(app) {
	app.run([ '$rootScope', '$transitions','$httpRequest' function($rootScope, $transitions,$httpRequest) {
		$transitions.onSuccess({}, function(trans) {
			$(document).ready(function() {
				$rootScope.city = 10;
				$("select.select2").each(function() {
					$(this).select2();
				});
			});
		});
	} ]);

});