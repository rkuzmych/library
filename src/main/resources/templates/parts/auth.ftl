<#macro login path isRegisterForm>

<div class="row justify-content-center p-5">
    <div class="col-12 col-md-6 col-xl-4 ">
        <form class="form-sigin" method="post" action="${path}">
            <h2 class="form-signin-heading">
                <#if isRegisterForm>
                    Add new user
                    <#else>
                        Login
                </#if>
            </h2>
            <p>
                <label for="username">Username</label>
                <#if userExistError??>
                    <div class="invalid-feedback">
                        ${userExistError}
                    </div>
                 </#if>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
            </p>

            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
            </p>
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