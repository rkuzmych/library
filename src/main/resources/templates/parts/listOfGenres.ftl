<div class="row">
    <div class="col-4">
                <form method="post">
                    <p>Check the genre</p>
                    <#list genres as genre>
                        <p>
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">${genre.type}</label>
                        </p>
                    </#list>
                </form>
    </div>
</div>