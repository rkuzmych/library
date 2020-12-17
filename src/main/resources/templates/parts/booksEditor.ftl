<div class="col-12 col-md-6 col-xl-5 pt-5" style="min-height: 500px;">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="exampleFormControlFile1">Choose the foto of book</label>
                <input type="file" class="form-control-file" id="exampleFormControlFile1" name="photo">
            </div>

            <div class="form-group">
                <select name="author">
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

            <div class="form-group">
                <label for="exampleFormControlFile1">Choose pdf of book</label>
                <input type="file" class="form-control-file" name="pdf">
            </div>
            <#--csrf-->
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Save Book</button>
        </form>
</div>