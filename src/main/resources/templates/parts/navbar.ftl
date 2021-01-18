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

            <#if isMainPage??>
                <form class="form-inline form-search" method="get" enctype="multipart/form-data">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"
                           name="filter"
                           action="filter">
                    <button class="btn btn-light" type="submit">Search</button>
                </form>
            </#if>

            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/list">Users</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/books">Create Book</a>
                </li>
            </#if>
        </ul>
        <#if !isLoginPage??>
            <@a.log_button/>
        </#if>
    </div>
</nav>

<img src="/static/img/book.jpeg" class="img-fluid" alt="Responsive image">

