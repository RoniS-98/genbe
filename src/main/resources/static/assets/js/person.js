$(document).ready(function () {
  getPerson()
  addForm()
  postPerson()
  increment()
  postPendidikan()
});

function getPerson() {
  let  persons = $('#tablePerson')
  $.ajax({
    url: '/person',
    method: 'GET',
    success: function (res, status, xhr) {
      if (xhr.status == 200 || xhr.status == 201) {
        $(persons).DataTable({
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
              title: "Umur",
              data: "umur"
            },
            {
              title: "Jenjang",
              data: "jenjang"
            }
          ],
          "scrollX": true
        });
      } else {

      }
    },
    error: function (err) {
      console.log(err);
    }

  });
}

function postPerson(){
  var person = {};
  
  $("#btnSubmitPerson").click(function () {
    person.nik= $("#nik").val();
    person.name= $("#nama").val();
    person.alamat= $("#alamat").val();
    person.hp= $("#hp").val();
    person.tgl= $(".date").val();
    person.tempatLahir= $("#tempatLahir").val();
    // console.log(person);
    var personObj = JSON.stringify(person);
  
    $.ajax({
      type:'POST',
      url: 'person/trx',
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      data: personObj,
      success: function (status) {
        console.log(status)
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        });
        if (status.status=="true"){
            Toast.fire({
              icon:'success',
              type: 'success',
              title: 'status : ' + status.status + '\nmessage: ' + 'Data Anda Berhasil Dikrim'
            })

        }
      },
      error: function (status){
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        });
        Toast.fire({
          icon:'error',
          type: 'error',
          title: 'status : ' + status.status + '\nmessage: ' + 'NIK Kurang Dari 16 Digit atau Umur Anda Kurang Dari 30 Tahun'
        })
      }
    });
    console.log(personObj);
  });
}

function postPendidikan(){
  var pendidikan = [];
  var jenjang,institusi,tahunLulus,tahunMasuk;
  $("#btnSubmitPend").click(function () {
    if ($('#formPendidikan').parsley().validate()) {
      for (var i = 1; i<=id;i++){
        jenjang = $('#jenjang'+i).val();
        institusi= $('#institusi'+i).val();
        tahunLulus= $('#tahunLulus'+i).val();
        tahunMasuk= $('#tahunMasuk'+i).val();
  
        pendidikan.push({
          jenjang: jenjang,
          institusi:institusi,
          tahunMasuk:tahunMasuk,
          tahunLulus:tahunLulus,
        });
        console.log(pendidikan);
        var pendidikanObj = JSON.stringify(pendidikan);
      }
   
    console.log(pendidikanObj);
    $.ajax({
      type:'POST',
      url: 'education/person/'+$("#idPerson1").val(),
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      data: pendidikanObj,
      success: function () {
        // console.log(pendidikanObj);
        Swal.fire(
          'Data Anda Terkirim',
          '',
          'success'
        )
      },
      error: function (){
        alert("Data Kurang Lengkap");
      }
    });
    // console.log(pendidikanObj);
  }
  });
}

function addForm(){
  $(".addForm").click(function () {

    increment()
    $("#additional").append(
      '<fieldset class="card card-outline card-cyan">' +
      '<div class="form-group">'+
      '<label for="idPerson" class="col-sm col-form-label">ID Person</label>'+
      '<div class="col-sm">'+
      '<input type="text" class="form-control" required placeholder="Nomor Identitas" id="idPerson' + id +'">'+
      '</div>'+
      '</div>'+
      '<div class="form-group" id="add">' +
      '<label class="col-sm-2 col-form-label">Jenjang</label>' +
      '<div class="col-sm-12">' +
      ' <label class="mr-sm-2 sr-only" for="jenjang">Preference</label>' +
      '<select class="custom-select mr-sm-2" required id="jenjang' + id +'">' +
      '   <option selected>Pilih...</option>' +
      '<option value="sd">SD</option>' +
      '<option value="smp">SMP</option>' +
      '<option value="sma">SMA</option>' +
      '<option value="s1">S1</option>' +
      '<option value="s2">S2</option>' +
      '<option value="s3">S3</option>' +
      '</select>' +
      '</div>' +
      '</div>' +
      '<div class="form-group">' +
      '<label for="institusi" class="col-sm col-form-label">Institusi</label>' +
      '<div class="col-sm">' +
      '<input type="text" class="form-control" id="institusi' + id +'"required placeholder="Institusi">' +
      '</div>' +
      '</div>' +
      '<div class="form-group">' +
      ' <label for="tahunMasuk" class="col-sm-2 col-form-label">Tahun Masuk</label>' +
      ' <div class="col-sm-12">' +
      ' <input type="alamat" class="form-control" id="tahunMasuk' + id +'" required placeholder="Tahun Masuk">' +
      '</div>' +
      '</div>' +
      '<div class="form-group">' +
      '<label for="tahunLulus" class="col-sm col-form-label">Tahun Lulus</label>' +
      '<div class="col-sm">' +
      '<input type="text" class="form-control" id="tahunLulus' + id +'" required placeholder="Tahun Lulus">' +
      ' </div>' +
      '</div>' +
      '<button type="button" class="btn btn-danger float-left remAdd" href="javascript:void(0);">Remove</button>' +
      ' </div>' +
      '</fieldset>'
    );
  });
  $('#additional').on('click', '.remAdd', function () {
    $(this).parent().remove();
    id--;
  });

}


var id = 0;
function increment(){
  id += 1; /* Function for automatic increment of field's "Name" attribute. */
}




