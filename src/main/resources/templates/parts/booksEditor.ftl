<div class="col-12 col-md-6 col-xl-5 pt-5" style="min-height: 500px;">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleFormControlFile1">
                <#if book??>
                    Photo of book is chused
                <#else>
                    Choose the foto of book
                </#if>
            </label>
            <input type="file" class="form-control-file" name="photo">
        </div>

        <div class="form-group">
            <select name="author">
                <#if isEditorForm??>
                    <option name="allAuthors"><#if book??>${book.author.name}<#else> Choose author</#if></option>
                </#if>
                <#list authors as author>
                    <option>${author.name}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <input type="text" name="name" value="<#if book??>${book.name}</#if>"
                   placeholder="name"/>
        </div>

        <div class="form-group">
            <select name="genre">
                <#if isEditorForm??>
                    <option><#if book??>${book.genre.type}<#else>Choose genre</#if></option>
                </#if>

                <#list genres as genre>
                    <option>${genre.type}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <input type="text" name="pageCount" value="<#if book??>${book.pageCount}</#if>"
                   placeholder="pageCount"/> <br/>
        </div>

        <div class="form-group">
            <input type="text" name="publishYear" value="<#if book??>${book.publishYear}</#if>"
                   placeholder="publishYear"/> <br/>
        </div>

        <input type="hidden" name="id" value="<#if book??>${book.id}</#if>"/>

        <div class="form-group">
            <label for="exampleFormControlFile1">
                <#if book??>
                    PDF of book is chused
                <#else>
                    Choose the PDF of book
                </#if>
            </label>
            <input type="file" class="form-control-file" name="pdf">
        </div>
        <#--csrf-->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Save Book</button>
    </form>
</div>