<div class="row pb-5 search-block">
    <div class="col-sm">
        <form class="form-inline" method="get" enctype="multipart/form-data">
            <#include "listOfGenres.ftl" />
            <#include "listOfAuthors.ftl" />
            <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Filter</button>
        </form>
    </div>

    <div class="col-sm">
        <form class="form-inline" method="get" enctype="multipart/form-data">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="filter" action="filter">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</div>