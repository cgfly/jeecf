define([ 'jquery', 'app' ], function($, app) {
	app.factory('$zTreecm', function() {
		return {
			setting : function(config){
			   return setting = {
						check: {
						   enable: true,
						   chkStyle: "checkbox",
						   chkboxType: { "Y": "s", "N": "ps" }
						},
						data: {
						   simpleData: {
							   enable: true,
							   idKey: "id",
							   pIdKey: "parentId",
							   rootPId: ""
						   }
						},
						callback: {
						    beforeClick: function (treeId, treeNode) {
						        var zTree = $.fn.zTree.getZTreeObj(config.treeId);
						        if (treeNode.isParent) {
						            zTree.expandNode(treeNode);
						            return false;
						        } else {
						            demoIframe.attr("src", treeNode.file + ".html");
						            return true;
						        }
						    },
						    onCheck: function (e,treeId,treeNode){
						    	config.onCheck(e,treeId,treeNode);
						    }
				      }
			}
		 }
	   }
	});
});