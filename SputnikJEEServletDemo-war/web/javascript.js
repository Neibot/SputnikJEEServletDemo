function AddToCatalog(catalog)
{
    var tempObject =
    {            
        "name":document.getElementById('name_add').value,
        "author":document.getElementById('author_add').value,
        "publication_date":document.getElementById('publication_date_add').value
    };
    var json_string = encodeURI(JSON.stringify(tempObject));
    document.addToCatalogForm.action = "controller?action=send&json="+json_string+"&catalog="+catalog;
}

function EditInCatalog(catalog)
{
    var tempObject =
    {   
        "id":document.getElementById('id_edit').value,
        "name":document.getElementById('name_edit').value,
        "author":document.getElementById('author_edit').value,
        "publication_date":document.getElementById('publication_date_edit').value
    };
    var json_string = encodeURI(JSON.stringify(tempObject));
    document.addToCatalogForm.action = "controller?action=edit&json="+json_string+"&catalog="+catalog;
}

function SearchInCatalog(catalog)
{    
    var id = document.getElementById('id_search').value;
    document.addToCatalogForm.action = "controller?action=receive&id="+id+"&catalog="+catalog;
}

function RemoveFromCatalog(catalog)
{    
    var id = document.getElementById('id_remove').value;
    document.addToCatalogForm.action = "controller?action=remove&id="+id+"&catalog="+catalog;
}

function ChangeCatalog(newcatalog)
{    
    var id = document.getElementById('id_change').value;
    document.addToCatalogForm.action = "controller?action=changecatalog&id="+id+"&catalog="+newcatalog;
}

function OnSubmitForm() 
{
    if(document.pressed === 'Добавить в публичный каталог') 
    {        
        AddToCatalog('public');
    } 
    else if(document.pressed === 'Добавить в закрытый каталог') 
    {
        AddToCatalog('private');
    }
    else if(document.pressed === 'Искать в публичном каталоге') 
    {
        SearchInCatalog('public');
    }
    else if(document.pressed === 'Искать в закрытом каталоге') 
    {
        SearchInCatalog('private');
    }
    else if(document.pressed === 'Отредактировать в публичном каталоге') 
    {
        EditInCatalog('public');
    }
    else if(document.pressed === 'Отредактировать в закрытом каталоге') 
    {
        EditInCatalog('private');
    }
    else if(document.pressed === 'Удалить в публичном каталоге') 
    {
        RemoveFromCatalog('public');
    }
    else if(document.pressed === 'Удалить в закрытом каталоге') 
    {
        RemoveFromCatalog('private');
    }
    else if(document.pressed === 'Из публичного в закрытый') 
    {
        ChangeCatalog('private');
    }
    else if(document.pressed === 'Из закрытого в публичный') 
    {
        ChangeCatalog('public');
    }
    return true;
}

var myCalendar;
function doOnLoad() 
{
    myCalendar = new dhtmlXCalendarObject(["publication_date_add","publication_date_edit"]);
}


