$(document).ready(function () {
  tablePendidikan.create();
});
$('#btn-tambah-pendidikan').click(function () {
  formPendidikan.resetForm();
  $('#modal-pendidikan').modal('show')
});

$('#btn-save-biodata').click(function () {
  formPendidikan.saveForm()
})

var tablePendidikan = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tablePendidikan')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tablePendidikan').DataTable().clear();
      $('#tablePendidikan').DataTable().destroy();
    }

    $.ajax({
      url: '/education',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tablePendidikan').DataTable({
            data: res,
            columns: [
              {
                title: "Jenjang",
                data: "jenjang"
              },
              {
                title: "Institusi",
                data: "institusi"
              },
              {
                title: "Tahun Lulus",
                data: "tahunLulus"
              },
              {
                title: "Tahun Masuk ",
                data: "tahunMasuk"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  // console.log(data);
                  return "<button class='btn-info' onclick=formPendidikan.setEditData('" + data.idPerson + "')>Edit</button>"
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

var formPendidikan = {
  resetForm: function () {
    $('#form-pendidikan')[0].reset();
    //   $('#idBiodata').val('');
    //   $('#idPerson').val('');

  },
  saveForm: function () {
    if ($('#form-pendidikan').parsley().validate()) {
      var dataResult = getJsonForm($("#form-pendidikan").serializeArray(), true);
      var hasil = [dataResult];
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
      });
      $.ajax({
          url: '/education/person/' +$("#idPerson").val(),
          method: 'post',
          contentType: 'application/json',
          dataType: 'json',
          data: JSON.stringify(hasil),

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
              $('#modal-pendidikan').modal('hide')
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
        console.log(JSON.stringify(dataResult))
      );
    }
  }, setEditData: function (idPerson) {
    formPendidikan.resetForm();

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
