define([  'jquery', 'app' ], function($, app) {
	app.factory('$jBoxcm',['$compile', function($compile) {
		return {
			table : function($scope,obj,target){
			        var id = $(obj).attr("id");
			        var dataMsgId = id+"DataMsg";
			        var tableId = id+"Table";
			        var dataListId = id + "DataList";
			        var dataMsg = eval("("+ $(obj).attr("data-msg")+")");
			        var keys = dataMsg.key.split(",");
			        var ops = eval("("+ $(obj).attr("data-ops")+")");
				    $scope[dataMsgId] =dataMsg;
				    if($("#"+tableId).html() != undefined){
				    	$("#"+tableId).remove();	
				    }
				    var template  = '<div ng-model="'+dataMsgId+'" ng-model="'+dataListId+' " id="'+tableId+'"><table class="table table-bordered table-hover"><thead><tr><th ng-repeat="(k,table) in '+dataMsgId+'.tableFields track by $index">{{table.name}}</th></tr></thead><tbody><tr ng-repeat="data in '+dataListId+' track by $index" data-name="{{ data.'+ dataMsg.name+'}}"  data-value="{{ $index }}"><td ng-repeat="table in '+dataMsgId+'.tableFields" >{{ data[table.label] }}</td></tr></tbody></table></div>';
				    var tableContainer =  this.modalConfig(ops,{
				    	success : function(response) {
							  var data = response.data;
							  var values = dataMsg.value.split(",");
							  $scope[dataListId]  = data;
							  
							  tableContainer.setContent($compile(template)($scope));
							  setTimeout(function(){ 
								  $("#"+ tableId +" tbody tr").each(function(){
                            	      $(this).dblclick(function(){ 
                            	    	  $(obj).find('input[name="selectedName"]').val($(this).attr("data-name"));
                            	    	  var index = $(this).attr("data-value");
                            	    	  $scope.$apply(function () {  
                            	    		  for(var i in keys){
 	                            	    		 target[keys[i]] = data[index][values[i]];
 	                            	    	  }
                            	    	  });
                            	    	  tableContainer.close();
                            	      });
                                  });
							  }, 50 );				    		
				    	},
					   clear : function(){
						   $(obj).find('input[name="selectedName"]').val("");
						   $scope.$apply(function () {  
          	                  for(var i in keys){
                 	              target[keys[i]] = "";
                              }
          	               });
						   tableContainer.close();
					   }
				    });
				    return tableContainer;
			},
			treeTable : function($scope,obj,target){
				    var id = $(obj).attr("id");
			        var dataMsgId = id+"DataMsg";
			        var tableId = id+"Table";
			        var dataListId = id + "DataList";
			        var dataMsg = eval("("+ $(obj).attr("data-msg")+")");
			        var keys = dataMsg.key.split(",");
			        var ops = eval("("+ $(obj).attr("data-ops")+")");
			        ops.id = "#"+id;
				    $scope[dataMsgId] =dataMsg;
				    var template = "";
				    if($("#"+tableId).html() != undefined){
				    	$("#"+tableId).remove();	
				    }
			        template  = '<div ng-model="'+dataMsgId+'" ng-model="'+dataListId+' " ><table class="table table-bordered table-hover" id="'+tableId+'"><thead><tr><th ng-repeat="(k,table) in '+dataMsgId+'.tableFields track by $index">{{table.name}}</th></tr></thead><tbody><tr ng-repeat="data in '+dataListId+' track by $index" data-name="{{ data.'+ dataMsg.name+'}}"  data-value="{{   $index }}" data-tt-id="{{  data.'+dataMsg.ttId+'}}" data-tt-parent-id="{{  data.'+dataMsg.ttParentId+'}}"><td ng-repeat="table in '+dataMsgId+'.tableFields" >{{ data[table.label] }}</td></tr></tbody></table></div>';
				    var tableContainer =  this.modalConfig(ops,{
					   success : function(response) {
						   var data = response.data;
						   var values = dataMsg.value.split(",");
						   $scope[dataListId]  = data;
						   tableContainer.setContent($compile(template)($scope));
						   setTimeout(function(){ 
								$("#"+ tableId).treetable({ expandable: true },true);
								$("#"+ tableId +" tbody tr").each(function(){
                         	       $(this).dblclick(function(){ 
                         	           $(obj).find('input[name="selectedName"]').val($(this).attr("data-name"));
                         	           var index = $(this).attr("data-value");
                         	           $scope.$apply(function () {  
                         	               for(var i in keys){
	                            	           target[keys[i]] = data[index][values[i]];
	                                       }
                         	           });
                         	           tableContainer.close();
                                   });
                               });
					       }, 50 )  
					   },
					   clear : function(){
						   $(obj).find('input[name="selectedName"]').val("");
						   $scope.$apply(function () {  
             	               for(var i in keys){
                    	           target[keys[i]] = "";
                               }
             	           });
						   tableContainer.close();
					   }
				   });
				   return tableContainer;
			},
			success : function(content){
				if (content == undefined || content == ""){
					content = "操作成功";
				}
				var ops = {color:"green",content:content,autoClose:5000,closeOnClick:'box',overlay:false}
			    return this.noticeConfig(ops);
			},
			error : function(content){
				if (content == undefined || content == ""){
					content = "操作失败";
				}
				var ops = {color:"red",content:content,autoClose:5000,closeOnClick:'box',overlay:false}
			    return this.noticeConfig(ops);
			},
			loading : function(content){
				if (content == undefined || content == ""){
					content = "正在提交，请稍等...";
				}
				var ops = {color:"blue",content:content,autoClose:false,closeOnClick:false,overlay:true}
				return this.noticeConfig(ops);
			},
			delConfrim : function($scope){
				return this.confirmConfig({content:"确定是否删除此项"},{
					confirm : function(data){
						$scope.deleteForm(data);
					}
				});
			},
			modalConfig : function(ops,callback){
				var tableContainer =  new jBox('Modal', {
					  attach:ops.id,
					  width: ops.width,
					  height: ops.height,
					  blockScroll: false,
					  animation: 'zoomIn',
				      closeButton: 'title',
				      footer: '<button class="button-clear">清空</button>',
					  overlay: true,
					  reposition: false,
					  repositionOnOpen: false,
					  target:ops.id,
					  position:{
						  x :"left",
						  y:"top"
					  },
					  outside:"y",
					  onCreated: function () {
				          this.footer.find('.button-clear').on('click', function () {
				        	  callback.clear();
				          });
				      },
					  ajax :　{
						  url: ops.url,
					      type:"POST", 
					      contentType : 'application/json',
					      reload: 'strict', 
					      setContent: false,
			    	      beforeSend: function() {
					          this.setContent('');
					          this.setTitle('<div class="ajax-sending">'+ops.title+'</div>');
					      },
					      reload: 'strict',
					      setContent: false,
						  success: function (response) {
							  callback.success(response);
						  }				 
					  }
     			  });
				  return tableContainer;
			},
			confirmConfig : function(ops,callback){
				new jBox('Confirm', {
					content: ops.content,
					cancelButton: '取消',
					confirmButton: '确定',
					getContent: "",
				    _onAttach: function (el){  
				      if (!this.options.confirm) {
				        var submit = el.attr('ng-click') ? el.attr('ng-click') : (el.attr('href') ? (el.attr('target') ? 'window.open("' + el.attr('href') + '", "' + el.attr('target') + '");'  : 'window.location.href = "' + el.attr('href') + '";') : '');
				        submit = el.attr("data-content");
				        el.prop('ng-click', null).data('jBox-Confirm-submit', submit);
				      }
				    },
				    _onOpen: function () {
				       this.submitButton.off('click.jBox-Confirm' + this.id).on('click.jBox-Confirm' + this.id, function () { 
				          callback.confirm(this.source.data('jBox-Confirm-submit'));
				          this.options.closeOnConfirm && this.close(); 
				       }.bind(this));
				   }
				    
				});
			},
			noticeConfig:function(ops){
				return  new jBox('Notice', {
				      attributes: {
				    	x: 'right',
				        y: 'top'
				      },
				      offset : {
				    	  y : window.innerHeight/4,
				    	  x : window.innerWidth/2-ops.content.length*5
				      },
				      stack: false,
				      animation: {
				        open: 'tada',
				        close: 'zoomIn'
				      },
				      autoClose: ops.autoClose,
				      closeOnClick: ops.closeOnClick,
				      overlay: ops.overlay,
				      color: ops.color,
				      content: ops.content,
				      delayOnHover: true,
				      showCountdown: true,
				      closeButton: false
				    });
			}
		}
	}]);
});