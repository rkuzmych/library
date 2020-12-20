<#import "parts/common.ftl" as c>

<@c.page>
    <div style="min-height: 550px;">
    <table class="table p-5">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row">${user.getId()}</th>
                <td>${user.getUsername()}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.getId()}">Edit</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>
</@c.page>
