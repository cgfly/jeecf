define([ 'jquery', 'app' ], function($, app) {
	app.factory('$genHelper', function() {
		var genJson = {
			 "type" : [
				     { "value":"String","label" :"String","jdbcTypes":"varchar,text"},
					 { "value":"Long","label" :"Long","jdbcTypes":"bigint"},
				     { "value":"Integer","label" :"Integer","jdbcTypes":"integer,int,tinyint"},
					 { "value":"Double","label" :"Double","jdbcTypes":"double"},
				     { "value":"java.util.Date","label" :"Date","jdbcTypes":"date,datetime,timestamp"},
					 { "value":"java.math.BigDecimal","label" :"BigDecimal","jdbcTypes":"bigdecimal"}
				],
			    "queryType" : [
				     { "value":"0", "label":"=" },
				     { "value":"1", "label":"!=" },
				     { "value":"2", "label":"> or <" },
				     { "value":"3", "label":">= or <=" },
				     { "value":"4", "label":"like" },
				     { "value":"5", "label":"like%" },
				],
				"formType" : [
				     { "value":"0", "label":"文本框","jdbcTypes":"varchar,char" },
				     { "value":"1", "label":"文本域","jdbcTypes":"text"},
				     { "value":"2", "label":"数字框","jdbcTypes":"Integer,int,tinyint,bigint,long,double,bigdecimal" },
				     { "value":"3", "label":"时间框","jdbcTypes":"date,datetime,timestamp"},
				     { "value":"4", "label":"下拉框","jdbcTypes":""},
				     { "value":"5", "label":"表格下拉框","jdbcTypes":""}
				]
			}
		
		
		return {
			getGenJson : function(){
				return genJson;
			},
			getTypes : function(){
				return genJson["type"];
			},
			getQueryTypes : function(){
				return genJson["queryType"];
			},
			getFormTypes : function(){
				return genJson["formType"];
			},
			toType : function(jdbcType){
				var type = jdbcType.split("(")[0];
				var types = this.getTypes();
				for(j in types){
				   var jdbcTypes = types[j]["jdbcTypes"].split(",");
				   for(k in jdbcTypes){
					  if(jdbcTypes[k] == type)
					     return types[j];
				   }
				}
				return "";
			},
			toField : function(jdbcField){
			    var re=/_(\w)/g;
			    return jdbcField.replace(re,function ($0,$1){
			        return $1.toUpperCase();
			    });
			},
			toFormType : function(jdbcType){
				var type = jdbcType.split("(")[0];
				var fromTypes = this.getFormTypes();
				for(j in fromTypes){
				   var jdbcTypes = fromTypes[j]["jdbcTypes"].split(",");
				   for(k in jdbcTypes){
					  if(jdbcTypes[k] == type)
					     return fromTypes[j];
				   }
				}
				return "";
			},
			isKey : function(key){
				if(key == "1")
					return {"value":"1","label":"是"};
				return {"value":"0","label":""};
			}
		}
		
		
	
	});
});