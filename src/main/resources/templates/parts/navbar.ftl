<#include "security.ftl">
<#import "auth.ftl" as a>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">LiBooks</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/list">Users</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/books">Create Book</a>
                </li>
            </#if>
        </ul>
        <@a.log_button/>
    </div>
</nav>
