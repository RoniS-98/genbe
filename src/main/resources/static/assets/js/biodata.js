$(document).ready(function () {

  tableBiodata.create();
});
$('#btn-tambah-biodata').click(function () {
  formBiodata.resetForm();
  $('#modal-biodata').modal('show')
});

$('#btn-save-biodata').click(function () {
  formBiodata.saveForm()
})

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
                  // console.log(data);
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
    $('#idBiodata').val('');
    $('#idPerson').val('');

  },
  saveForm: function () {
    if ($('#form-biodata').parsley().validate()) {
      var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
      });
      $.ajax({
        url: '/person/trx',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),

        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableBiodata.create();
            if (status.status=="true"){
              
            } else{
            }
            Toast.fire({
              icon:'success',
              type: 'success',
              title: 'status : ' + 'Sukses' + '\n' + 'Data Anda Berhasil Dikirim'
            })
            $('#modal-biodata').modal('hide')
          }
        },
        error: function (status) {
          Toast.fire({
            icon:'error',
            type: 'error',
            title: 'status : ' + 'Gagal' + '\n ' + 'Data Anda Gagal Dikirim'
          })
          if (status.status=="false"){
          }
        }
      },
      // console.log(JSON.stringify(dataResult))
      );
    }
  }, setEditData: function (idPerson) {
    formBiodata.resetForm();

    $.ajax({
      url: '/person/biodata/' + idPerson,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          console.log(res)
          $('#form-biodata').fromJSON(JSON.stringify(res));
          $('#form-biodata').fromJSON(JSON.stringify(res.person));
          $('#modal-biodata').modal('show')

        }
      },
      error: function (err) {
        console.log(err);
      }
    });

  }

};
