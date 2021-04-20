+   首先重建一个备份索引

  命令：

  -  首先在复合查询中查询框填写 “http://12.0.0.93:39200/all_person_index_bak”, 12.0.0.93:39200是你那边的es地址，all_person_index_bak 是备份索引的名字

  -  方式选择 “PUT”

  -  在”put” 下方的框中，填写以下信息

    ````json
    {
    	"settings": {
    		"index": {
    			"number_of_shards": "12",
    			"number_of_replicas": "1"
    		}
    	},
    	"mappings": {
    		"all_person_type": {
    			"properties": {
    				"birthday": {
    					"type": "keyword"
    				},
    				"gender": {
    					"type": "keyword"
    				},
    				"city": {
    					"type": "keyword"
    				},
    				"nation": {
    					"type": "keyword"
    				},
    				"model_fail": {
    					"type": "integer"
    				},
    				"model_high": {
    					"type": "integer"
    				},
    				"certificate_number": {
    					"type": "text",
    					"fields": {
    						"keyword": {
    							"ignore_above": 256,
    							"type": "keyword"
    						}
    					}
    				},
    				"human_id": {
    					"type": "keyword"
    				},
    				"error_reason": {
    					"type": "text",
    					"fields": {
    						"keyword": {
    							"ignore_above": 256,
    							"type": "keyword"
    						}
    					}
    				},
    				"update_time": {
    					"type": "keyword"
    				},
    				"model_low": {
    					"type": "integer"
    				},
    				"province": {
    					"type": "keyword"
    				},
    				"pic_error_govern_tag": {
    					"type": "integer"
    				},
    				"low_govern_tag": {
    					"type": "integer"
    				},
    				"certificate_govern_tag": {
    					"type": "integer"
    				},
    				"manage_police": {
    					"type": "keyword"
    				},
    				"social_status": {
    					"type": "integer"
    				},
    				"passthrough_field": {
    					"type": "keyword"
    				},
    				"pic_missing_govern_tag": {
    					"type": "integer"
    				},
    				"main_pic_url": {
    					"type": "keyword"
    				},
    				"police_phone": {
    					"type": "keyword"
    				},
    				"native_county_code": {
    					"type": "keyword"
    				},
    				"certificate_tag": {
    					"type": "integer"
    				},
    				"address": {
    					"type": "keyword"
    				},
    				"low_pic_id": {
    					"type": "keyword"
    				},
    				"pic_tag": {
    					"type": "integer"
    				},
    				"case_code": {
    					"type": "keyword"
    				},
    				"create_time": {
    					"type": "keyword"
    				},
    				"operate_tag": {
    					"type": "integer"
    				},
    				"human_pic_id": {
    					"type": "keyword"
    				},
    				"certificate_encryp": {
    					"type": "keyword"
    				},
    				"source_type": {
    					"type": "integer"
    				},
    				"analysis": {
    					"type": "keyword"
    				},
    				"model_success": {
    					"type": "integer"
    				},
    				"gender_card": {
    					"type": "keyword"
    				},
    				"lib_code": {
    					"type": "keyword"
    				},
    				"low_pic_url": {
    					"type": "keyword"
    				},
    				"name": {
    					"type": "text",
    					"fields": {
    						"keyword": {
    							"ignore_above": 256,
    							"type": "keyword"
    						}
    					}
    				},
    				"residence_county_code": {
    					"type": "keyword"
    				},
    				"certificate_type": {
    					"type": "integer"
    				},
    				"is_modeled": {
    					"type": "integer"
    				},
    				"cas_cade": {
    					"type": "integer"
    				},
    				"model_error": {
    					"type": "integer"
    				}
    			}
    		}
    	},
    	"primary_terms": {
    		"0": 22,
    		"1": 21,
    		"2": 27,
    		"3": 26,
    		"4": 24,
    		"5": 18,
    		"6": 13,
    		"7": 20,
    		"8": 19,
    		"9": 26,
    		"10": 13,
    		"11": 15
    	}
    }
    ````
  
  出现红框的提示，表示重建成功
  
  ![image-20201228163321221](https://img2020.cnblogs.com/blog/1625166/202012/1625166-20201228163326459-2020601624.png)


    

+ 将数据移至到备份索引中

  - 首先在复合查询中查询框填写 “http://12.0.0.93:39200/_reindex/”

  - 方式选择 "POST"

  - 复制以下命令

    ````json
    {
      "source": {
        "index": "all_person_index",
    	"size":10000  
      },
      "dest": {
        "index": "all_person_index_bak"
      }
    }
    ````

  - 最后在基本查询中，查看两个索引 "all_person_index" 和 ”all_person_index_bak“的数据量是否相同

  