<#macro login path isRegisterForm>
    <#include "security.ftl">

<div class="row justify-content-center p-5" style="min-height: 580px;">
    <div class="col-12 col-md-6 col-xl-4 ">
        <form class="form-sigin" method="post" action="${path}">
            <h2 class="form-signin-heading">
                <#if isRegisterForm>
                    Add new user
                    <#else>
                        Login
                </#if>
            </h2>
            <#if message??>
                ${message}
            </#if>
            <p>
                <label for="username">Username</label>
                <#if userExistError??>
                    <div class="invalid-feedback" class="text-danger">
                        ${userExistError}
                    </div>
                 </#if>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
            </p>

            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                       required>
            </p>

            <#if isRegisterForm>
            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password2" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password confirmation" required>
            </p>
                <p class="text-danger">
                <#if passwordError??>
                        ${passwordError}
                </#if>
                </p>
            </#if>
            <#--csrf-->
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <#if isRegisterForm>
                    Create
                <#else>
                    Sign In
                </#if>
            </button>
        </form>

        <#if !isRegisterForm>
            <a href="/registration" class="pt-3">Add new user</a>
        </#if>
    </div>
</div>
</#macro>

<#--Button Log in/ Log out-->
<#include "security.ftl">
<#macro log_button>

    <#if isActive>
        <form action="/logout" method="post" class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-danger" type="submit">
                Log out
            </button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>

        <#else>
            <form class="form-inline my-2 my-lg-0">
                <a class="btn btn-primary" href="/login">Log in</a>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
    </#if>
</#macro>