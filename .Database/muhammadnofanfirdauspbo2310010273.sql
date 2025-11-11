-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 11, 2025 at 12:56 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `muhammadnofanfirdauspbo2310010273`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_spdp`
--

CREATE TABLE `tb_detail_spdp` (
  `id_detail_spdp` varchar(10) NOT NULL,
  `id_spdp` varchar(10) DEFAULT NULL,
  `id_pegawai` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id_pegawai` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(100) DEFAULT NULL,
  `nip` varchar(100) DEFAULT NULL,
  `nrp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id_pegawai`, `nama`, `jabatan`, `nip`, `nrp`) VALUES
('1', 'kacaw', 'ketua', '23100', '23');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pengguna`
--

CREATE TABLE `tb_pengguna` (
  `id_pengguna` varchar(10) NOT NULL,
  `foto` varchar(225) DEFAULT NULL,
  `kata_sandi` varchar(225) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `pengguna` varchar(100) DEFAULT NULL,
  `peran` enum('admin','pegawai','umum') DEFAULT 'umum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_spdp`
--

CREATE TABLE `tb_spdp` (
  `id_spdp` varchar(10) NOT NULL,
  `pasal` varchar(50) DEFAULT NULL,
  `tanggal_surat` date DEFAULT NULL,
  `status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'baru',
  `id_pengguna` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_tersangka`
--

CREATE TABLE `tb_tersangka` (
  `id_tersangka` varchar(10) NOT NULL,
  `nik` varchar(100) DEFAULT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL,
  `jk` varchar(10) DEFAULT NULL,
  `agama` varchar(100) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `id_spdp` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_spdp`
--
ALTER TABLE `tb_detail_spdp`
  ADD PRIMARY KEY (`id_detail_spdp`),
  ADD KEY `id_spdp` (`id_spdp`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indexes for table `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `tb_pengguna`
--
ALTER TABLE `tb_pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `tb_spdp`
--
ALTER TABLE `tb_spdp`
  ADD PRIMARY KEY (`id_spdp`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- Indexes for table `tb_tersangka`
--
ALTER TABLE `tb_tersangka`
  ADD PRIMARY KEY (`id_tersangka`),
  ADD KEY `id_spdp` (`id_spdp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
