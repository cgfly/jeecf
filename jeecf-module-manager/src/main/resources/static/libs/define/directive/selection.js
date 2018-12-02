define(['app'], function (app) {
	app.directive('selection', function(){
		return {
			 restrict : 'E',
		     template : '<div class="" id="" data-msg="" data-ops="" ><input  class="btn btn-default" name="selectedName" /><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button></div> ',
		     replace : true,
		     controller : ['$scope', function($scope){
		 
		     }]
		}
		
	})
});