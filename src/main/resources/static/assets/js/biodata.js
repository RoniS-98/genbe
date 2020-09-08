var tableBiodata = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableBiodata')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableBiodata').DataTable().clear();
      $('#tableBiodata').DataTable().destroy();
    }

    $.ajax({
      url: '/person',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableBiodata').DataTable({
            data: res,
            columns: [
              {
                title: "NIK",
                data: "nik"
              },
              {
                title: "Nama",
                data: "name"
              },
              {
                title: "Alamat",
                data: "alamat"
              },
              {
                title: "HP",
                data: "hp"
              },
              {
                title: "Tanggal Lahir",
                data: "tgl"
              },
              {
                title: "Tempat Lahir",
                data: "tempatLahir"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {

                  return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.idPerson + "')>Edit</button>"
                }
              }
            ]
          });

        } else {

        }
      },
      error: function (err) {
        console.log(err);
      }
    });

  }
};

var formBiodata = {
  resetForm: function () {
    $('#form-biodata')[0].reset();
  },
  saveForm: function () {
    // if ($('#form-biodata').parsley().validate()) {
      var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

      $.ajax({
        url: '/person/trx',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableBiodata.create();
            $('#modal-biodata').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      },
      console.log(dataResult)
      );
    // }

  }, setEditData: function (idCabang) {
    formBiodata.resetForm();

    $.ajax({
      url: '/person/biodata/' + idCabang,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          console.log(res)
          $('#form-biodata').fromJSON(JSON.stringify(res));
          $('#form-biodata').fromJSON(JSON.stringify(res.person));
          // $('#form-biodata').fromJSON(JSON.stringify(res.biodata));

          $('#modal-biodata').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });

  }

};
