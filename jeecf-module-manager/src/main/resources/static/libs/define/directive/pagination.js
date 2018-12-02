/**
 * 分页插件
 * 
 * @param app
 * @returns
 */
define(['app'], function (app) {
	app.directive('pagePagination', function(){
	    return {
	        restrict : 'E',
	        template : '<div class="pagination-box"><ul class="pagination"><li ng-class="page.style" ng-repeat="page in pageList"><a  ng-click="searchForm({message:this})" >{{ page.name }}</a></li></ul><ul class="pagination" ng-if="pageList[0]"><li class="page-count disabled"><span>共 <b>{{ pageRecord }}</b> 条记录 / 共 <b>{{ pageCount }}</b> 页</span></li></ul></div>',
	        replace : true,
	        scope : {
	            "pageId"            : "=",
	            "pageRecord"        : "=",
	            "pageSize"          : "=",
	            "pageUrlTemplate"   : "=",
	            "pageCount" : "=",
	            "pageList" : "=",
	            "searchForm" : "&searchForm",
	        },
	        controller : ['$scope', function($scope){
	             
                $scope.getLink = function(pageId){
	                return $scope.pageUrlTemplate.replace("{PAGE}", pageId);
	            };
	 
	            $scope.getPageList = function(){
	                var page = [];
	                var firstPage = parseInt(( parseInt($scope.pageId) ) / $scope._pageSize ) * $scope._pageSize;
	                var lastLink = $scope.getLink($scope.pageCount);
	                var nextLink = $scope.getLink($scope.pageId + 1);
	                var prevLink =  $scope.getLink($scope.pageId - 1);
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
	                    link    : $scope.getLink(1)
	                });
	                page.push({
	                    name    : '上一页',
	                    style   : $scope.pageId == 1 ? "disabled" : "",
	                    link    : prevLink
	                });
	                for( var pageId = firstPage; pageId <= firstPage + $scope._pageSize ; pageId ++){
	                    if( pageId >= 1 && pageId <= $scope.pageCount ){
	                        page.push({
	                            name    : pageId,
	                            link    : $scope.getLink(pageId),
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
	            };

	            $scope.$watch('pageId', function(){
	                if( !isNaN($scope.pageId) ){
	                    if( !!$scope.pageSize ){
	                        $scope._pageSize = parseInt($scope.pageSize);
	                    }else{
	                        $scope._pageSize = 10;
	                    }
	                    $scope.pageId       = parseInt($scope.pageId);
	                    $scope.pageCount    = parseInt(( $scope.pageRecord - 1 ) / $scope._pageSize ) + 1;
	                    if( $scope.pageId < 1 ){
	                        $scope.pageId = 1;
	                    }else if( $scope.pageId > $scope.pageCount ){
	                        $scope.pageId = $scope.pageCount;
	                    }
	                    $scope.pageList     = $scope.getPageList();
	                }
	                $scope.pageLoad     = true;
	            });
	            $scope.pageLoad = false;
	        }]
	    }
	});
});