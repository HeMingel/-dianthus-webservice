#Dianthus系统数据库设计 
> **by** **HeMing**  


## 系统表
 
###人员表 d_sys_user
数据库字段  |  数据库类型  | 默认值|  字段说明   
-|-|:-:|-
id | varchar(32) | 	-	|	主键	 |
push_id | varchar |-| 平台推送过来的ID|
user_name|varchar(100)|-|姓名|
user_login_name|varchar|-|用户登录名称|
user_password|varchar|-|用户密码|
user_phone|varchar|-|用户联系电话
user_mail | varchar |-|用户邮件
org_id | varcahr(32)|-| 组织ID
last_login_time | varcahr |-| 上次登录时间
user_order	| int	|-|	用户排序
user_office_phone |	varhcar	|-| 座机
user_duty	| varchar	|-|用户职位
user_position	| varchar	|	-	| 用户职责
user_sex	| varchar	| -|	用户性别
parttime| integer	|-|是否兼任
create_time | timestamp | 当前系时间 |创建时间戳  |
enable_flag | int | 1 | 逻辑删除字段 0 已删除 1 正常使用 |

###组织表 d_sys_org
数据库字段  |  数据库类型  | 默认值|  字段说明   
-|-|:-:|-
id 	| 	varchar(32) | 	-	|	主键	 |
push_id	|	varchar	|	- |	平台推送过来的ID
org_name	|	varchar	|	-	|	组织名称
parent_id	|	varchar	|	-	|	上级组织ID
org_level	|	int	|	-	|	组织层级
org_order	|	int	|	-	|	组织排序
create_time	| timestamp | 当前系时间 |创建时间戳  |
enable_flag	| int | 1 | 逻辑删除字段 0 已删除 1 正常使用 |

### 角色表 d_sys_role
数据库字段  |  数据库类型  | 默认值|  字段说明   
-	|-	|	:-:	|	-	|
id 	| 	varchar(32) | 	-	|	主键	 |
role_name	| varchar(50)	|	-	|	角色名称	|
role_desc	| varchar 	|	-	| 角色说明
role_remark	| varchar	|	-	| 角色备注
create_time	| timestamp | 当前系时间 |创建时间戳  |
enable_flag	| int | 1 | 逻辑删除字段 0 已删除 1 正常使用 |

### 用户&角色表 d_sys_user_role
数据库字段  |  数据库类型  | 默认值|  字段说明   
-	|-	|	:-:	|	-	|
role_id	|	varchar(32)	|	-	| 角色ID
user_id	|	varchar(32)	|	-	|组织ID

###菜单表 d_sys_menu
数据库字段  |  数据库类型  | 默认值|  字段说明   
-	|-	|	:-:	|	-	|
id 	| 	varchar(32) | 	-	|	主键	 |
menu_name |	varchar(100)	|	-	| 菜单名称
menu_desc	| varchar	|	-	| 菜单说明
menu_remark	|	varchar 	| -	| 菜单备注
menu_order	|	int 	|	-	| 菜单排序
menu_url	|	varchar	|	-	| 菜单链接
parent_id	|	varchar	|	-	| 上级菜单ID
menu_ico	| 	varchar	|	-	| 图标
has_submenu	|	int	| 0	|	是否有子菜单 1 是 0 否
create_time	| timestamp | 当前系时间 |创建时间戳  |
enable_flag	| int | 1 | 逻辑删除字段 0 已删除 1 正常使用 

### 角色&菜单表 d_sys_role_menu
数据库字段  |  数据库类型  | 默认值|  字段说明   
-	|-	|	:-:	|	-	|
role_id	|	varchar(32)	|	-	| 角色ID
menu_id 	| 	varchar(32) | 	-	|	菜单主键	 

### 系统日志表 d_sys_log
数据库字段  |  数据库类型  | 默认值|  字段说明   
-	|-	|	:-:	|	-	|
id 	| 	varchar(32) | 	-	|	主键	 |
user_name	|	varchar	|	-	|	用户名称|
user_id		|	varchar(32)	|	-	|	用户ID	|
operation	|	int		|	-	|	操作 1 登陆 2 save 3 update 4 delete 5 select 6..
ip_address	|	varchar	|	-	| IP地址
operation_desc	| varchar	|	-	|	操作说明
operation_result	|	int 	|	-	| 操作结果
create_time	| timestamp | 当前系时间 |创建时间戳  |
enable_flag	| int | 1 | 逻辑删除字段 0 已删除 1 正常使用 		

### 系统字典表 d_sys_dictionary
数据库字段  |  数据库类型  | 默认值|  字段说明   
-|-|:-:|-
id | varchar(32) | 	-	|	主键	 |
parent_id	| varchar(32) |- | 上级id  
dict_name| varchar|-| 字典名
dict_value|varchar|-| 字典值
dict_type|varchar|-| 字典类型
dict_desc|text|-|字典说明
create_time | timestamp | 当前系时间 |创建时间戳  |
enable_flag | int | 1 | 逻辑删除字段 0 已删除 1 正常使用 |


##业务模块




