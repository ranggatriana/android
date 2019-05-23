<?php
// $_SERVER untuk menampilkan data dari server (sebuah fungsi)
// Returns the request method used to access the page (such as POST)
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // menangkap data dari post
    $id_user = $_POST['id_admin'];
    $username = $_POST['username'];
    $password = $_POST['password'];

    // mengubah password menjadi hash / diEnkirpsi
    $password = password_hash($password, PASSWORD_DEFAULT);

    // menyertakan sebuah file PHP kedalam file PHP lainnya
    require_once 'koneksidb.php';

    // query database
    $sql = "INSERT INTO tabel_admin VALUES ('$id_admin','$username','$password');";

    // memasukkan data kedalam database ,,, didalam if kondisi
    if (mysqli_query($conn, $sql)) {
        // membuat array untuk di transfer ke API
        $result["success"] = "1";
        $result["message"] = "success";

        // mengkorvesi data array dari $result menjadi data json / text dan data di echo
        echo json_encode($result);

        // menutup koneksi
        mysqli_close($conn);
    } else {
        // membuat array untuk di transfer ke API
        $result["success"] = "0";
        $result["message"] = "error";

        // mengkorvesi data array dari $result menjadi data json / text dan data di echo
        echo json_encode($result);

        // menutup koneksi
        mysqli_close($conn);
    }
}
