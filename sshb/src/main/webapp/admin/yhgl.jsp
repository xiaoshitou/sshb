<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhgl_datagrid').datagrid({
							url : '${pageContext.request.contextPath}/userAction!datagrid.action',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : 'name',
							sortOrder : 'asc',
							frozenColumns : [ [ {
								field : 'id',
								title : '编号',
								width : 150,
								checkbox : true
							}, {
								field : 'name',
								title : '登录名称',
								width : 150,
								sortable : true
							} ] ],
							columns : [ [
									{
										field : 'pwd',
										title : '密码',
										width : 150,
										formatter : function(value, row, index) {
											return '******';
										}
									}, {
										field : 'createdatetime',
										title : '创建时间',
										width : 150,
										sortable : true

									}, {
										field : 'modifydatetime',
										title : '最后修改时间',
										width : 150,
										sortable : true
									} ] ],
							toolbar : [ {
								iconCls : 'icon-add',
								text : '增加',
								handler : function() {
									$('#admin_yhgl_add').dialog('open');
									$('#admin_yhgl_addForm input').val('');
								}
							}, '-', {
								iconCls : 'icon-edit',
								text : '编辑',
								handler : function() {
									edit();
								}
							}, '-', {
								iconCls : 'icon-remove',
								text : '删除',
								handler : function() {
									remove();
								}
							} ],
							onDblClickRow : function(rowIndex, rowData) {
								alert(rowData.name);
								console.info(rowData);
							}
						});
		$('#admin_yhgl_addForm').form({
			 url:'${pageContext.request.contextPath}/userAction!add.action',
			 success : function(r) {
					var obj = jQuery.parseJSON(r);
					if (obj.code == '0000' ) {
						$('#admin_yhgl_add').dialog('close');
						$('#admin_yhgl_datagrid').datagrid('insertRow',{
							index:0,
							row:obj.obj
						});
					}
					$.messager.show({
						title : '提示',
						msg : obj.message
					});
				}
		});
	});
	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_serachForm')));
	};
	function clearFun() {
		$('#admin_yhgl_layout input[name=name]').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
	}
	function remove(){
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if(rows.length>0){
			$.messager.confirm('确认','您是否要删除当前选中的项目？',function(r){
				if (r){
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/userAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#admin_yhgl_datagrid').datagrid('load');
							$('#admin_yhgl_datagrid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.message
							});
						}
					})
				}
			});
		}else{
			$.messager.show({
				title:'提示',
				msg:'请勾选要删除的记录！',
				timeout:5000,
				showType:'slide'
			});
		}
		
	}
	function edit(){
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		if(rows.length == 1){
			var d = $('<div/>').dialog({
				width : 500,
				height : 200,
				href : '${pageContext.request.contextPath}/admin/yhglEdit.jsp',
				modal : true,
				title : '编辑用户',
				buttons : [{
					text:'编辑',
					handler : function(){
						$('#admin_yhgl_editForm').form('submit', {
							url: '${pageContext.request.contextPath}/userAction!edit.action',
							success: function(r){
								var obj = jQuery.parseJSON(r);
								if (obj.code == '0000') {
									d.dialog('close');
									$('#admin_yhgl_datagrid').datagrid('updateRow',
											{index:$('#admin_yhgl_datagrid').datagrid('getRowIndex',rows[0]),
											row:obj.obj
											});
								}
								$.messager.show({
									title : '提示',
									msg : obj.message
								});
							}
						});
						
					}
				}],
				onClose : function(){
					$(this).dialog('destroy');
				},
				onLoad : function(){
					$('#admin_yhgl_editForm').form('load',rows[0]);
				}
			
			});
		}else{
			$.messager.alert('提示','请选择一条记录进行编辑！');
		}
		
	}
</script>

<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height:120px;" align="center">
	<form id="admin_yhgl_serachForm">
		检索用户名称(可模糊查询)：<input name="name" /> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"onclick="searchFun();">查询</a> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="clearFun();">清空</a>
     </form>
	</div>

	<div data-options="region:'center'">
		<div id="admin_yhgl_datagrid"></div>
	</div>
</div>
<div id="admin_yhgl_add" class="easyui-dialog" title="增加人员" style="width: 500px;height:200px;" align="center"
	data-options="iconCls:'icon-save',closed:true,resizable:false,modal:true,buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){$('#admin_yhgl_addForm').submit();}
			},{
				text:'重置',
				iconCls:'icon-undo',
				handler:function(){$('#admin_yhgl_addForm input').val('');}
			}]" >
	<form id="admin_yhgl_addForm" method="post" >
		<table>
			<tr>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				
				<th>创建时间</th>
				<td>
				<input class="easyui-datetimebox" name="createdatetime" data-options="showSeconds:false"  style="width:150px" readonly="readonly">
				</td>
				<th>最后修改时间</th>
				<td><input class="easyui-datetimebox" name="modifydatetime" data-options="showSeconds:false"  style="width:150px" readonly="readonly">
				</td>
			</tr>
		</table>
	</form>
</div>




