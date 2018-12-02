define([ 'jquery', 'app' ], function($, app) {
	app.factory('$page',['$compile', function() {
		 var pageSize = 8;
		 return {    
			  getPageSize(){
				 return pageSize;
			  },
			  init : function($scope,size){
				    var result = true;
				    $scope.query = {urlTemplate : ""}, arr = [];
					$scope.query.list = (location.search == "" ? "?" : location.search).substr(1).split("&");
					for( var i = 0 ; i < $scope.query.list.length ; i++){
						if( $scope.query.list[i].indexOf("=") != -1 ){
							arr = $scope.query.list[i].split("=");
							$scope.query[arr[0]] = decodeURIComponent($scope.query.list[i].substr(arr[0].length+1));
							if( arr[0] != "page" ){
								$scope.query.urlTemplate += "&" + $scope.query.list[i];
							}
						}
					}
					$scope.query.urlTemplate = "?page={PAGE}#!"+$scope.currentRouteUrl;
					$scope.urlTemplate = $scope.query.urlTemplate;
					$scope.pageSize =size;
					var result = $scope.initPageBack({
						page : {current:$scope.pageId, size:size}
					});
                    if( !!$scope.pageSize ){
                        $scope._pageSize = parseInt($scope.pageSize);
                    }else{
                        $scope._pageSize = 10;
                    }
			  },
	          setPage : function($scope,total){
				  $scope.pageRecord = total;
				  $scope.query.urlTemplate = "?page={PAGE}#!"+$scope.currentRouteUrl;
				  $scope.urlTemplate = $scope.query.urlTemplate;
			      if( !$scope.query.page ){
					  $scope.pageId = 1;
				  }else{
				      $scope.pageId = parseInt($scope.query.page);
				  }
			      $scope.pageCount  = parseInt(( $scope.pageRecord - 1 ) / $scope._pageSize ) + 1;
                  $scope.pageList  =  this.getPageList($scope);
			  },
			  getPageId : function(message){
					var link = message.page.link;
					var pageId = -1;
					if (link != undefined) {
						if(link == "javascript:void(0)"){
							return pageId;
						}
						link = link.split("?page=")[1].split("#");
						pageId = parseInt(link[0]);

					} else {
						pageId = parseInt(message.page.name);
					}
					return pageId;
			  },
			  getPageList : function($scope){
	                var page = [];
	                var firstPage = parseInt(( $scope.pageId ) / $scope._pageSize ) * $scope._pageSize;
	                var lastLink = this.getLink($scope,$scope.pageCount);
	                var nextLink = this.getLink($scope,$scope.pageId + 1);
	                var prevLink = this.getLink($scope,$scope.pageId - 1);
	                if($scope.pageId == $scope.pageCount){
	                   lastLink = "javascript:void(0)";
	                   nextLink = "javascript:void(0)";
	                }
	                if($scope.pageId == 1){
	                	prevLink = "javascript:void(0)";
	                }
	               
	                page.push({
	                    name    : '首页',
	                    style   : $scope.pageId == 1 ? "disabled" : "",
	                    link    : this.getLink(1)
	                });
	                page.push({
	                    name    : '上一页',
	                    style   : $scope.pageId == 1 ? "disabled" : "",
	                    link    : prevLink
	                });
	                for( var pageId = firstPage; pageId <= firstPage + $scope._pageSize; pageId ++){
	                    if( pageId >= 1 && pageId <= $scope.pageCount ){
	                        page.push({
	                            name    : pageId,
	                            link    : this.getLink(pageId),
	                            style   : pageId == $scope.pageId ? "active" : ""
	                        });
	                    }
	                }
	                page.push({
	                    name    : '下一页',
	                    style   : $scope.pageId == $scope.pageCount ? "disabled" : "",
	                    link    : nextLink
	                });
	                page.push({
	                    name    : '尾页',
	                    style   : $scope.pageId == $scope.pageCount ? "disabled" : "",
	                    link    : lastLink
	                });
	                return page;
	               
	        },
            getLink : function($scope,pageId){
            	if($scope.urlTemplate != undefined) {
            		return $scope.urlTemplate.replace("{PAGE}", pageId);
            	}
            }
		}
	}]);
});