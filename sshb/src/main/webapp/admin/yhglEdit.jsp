<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_yhgl_editForm" method="post" >
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
				<input class="easyui-datetimebox" name="createdatetime" data-options="showSeconds:false"  style="width:150px" >
				</td>
				<th>最后修改时间</th>
				<td><input class="easyui-datetimebox" name="modifydatetime" data-options="showSeconds:false"  style="width:150px">
				</td>
			</tr>
			
		</table>
	</form>