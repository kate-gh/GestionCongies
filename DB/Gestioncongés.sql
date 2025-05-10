-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 25 avr. 2025 à 20:29
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestioncongies`
--

-- --------------------------------------------------------

--
-- Structure de la table `demandeconges`
--

DROP TABLE IF EXISTS `demandeconges`;
CREATE TABLE IF NOT EXISTS `demandeconges` (
  `id` int NOT NULL,
  `type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `dd` date NOT NULL,
  `df` date NOT NULL,
  `duree` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `demandeconges`
--

INSERT INTO `demandeconges` (`id`, `type`, `dd`, `df`, `duree`) VALUES
(8, 'Congé annuel payé', '2024-05-02', '2024-05-16', 14),
(10, 'Congé de maladie', '2024-06-01', '2024-06-02', 1);

-- --------------------------------------------------------

--
-- Structure de la table `gestionemploye`
--

DROP TABLE IF EXISTS `gestionemploye`;
CREATE TABLE IF NOT EXISTS `gestionemploye` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(35) COLLATE utf8mb4_general_ci NOT NULL,
  `acquis` float NOT NULL,
  `pris` float NOT NULL,
  `solde` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `gestionemploye`
--

INSERT INTO `gestionemploye` (`id`, `nom`, `prenom`, `email`, `acquis`, `pris`, `solde`) VALUES
(4, 'admin', 'admin', 'admin@admin.com', 11, 2, 9),
(8, 'omayma', 'harchich', 'omaima.harchich@gmail.com', 20, 0, 20),
(10, 'Kawtar', 'Gantouh', 'kawtargantouh@gmail.com', 20, 1, 19),
(12, 'liela', 'liela', 'liela@gmail.com', 18, 1, 17),
(14, 'yasser', 'yasser', 'yasser@gmail.com', 20, 2, 18);

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

DROP TABLE IF EXISTS `inscrire`;
CREATE TABLE IF NOT EXISTS `inscrire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `sexe` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `pass1` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `inscrire`
--

INSERT INTO `inscrire` (`id`, `nom`, `prenom`, `email`, `sexe`, `pass1`) VALUES
(1, 'Kawtar', 'Kawtar', 'kawtar@gmail.com', 'femme', 'kawtar'),
(2, 'Sara', 'Sara', 'S@gmail.com', 'femme', '1234'),
(3, 'Manal', 'manal', 'Manal@gmail.com', 'femme', '123'),
(4, 'Omayma', 'Omayma', 'omayma@gmail.com', 'femme', '123'),
(5, 'Nada', 'Nada', 'Nada@gmail.com', 'femme', '123');

-- --------------------------------------------------------

--
-- Structure de la table `insrire-employee`
--

DROP TABLE IF EXISTS `insrire-employee`;
CREATE TABLE IF NOT EXISTS `insrire-employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password1` int NOT NULL,
  `password2` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `insrire-employee`
--

INSERT INTO `insrire-employee` (`id`, `nom`, `prenom`, `email`, `password1`, `password2`) VALUES
(1, 'admin', 'admin', 'admin@gmail.com', 123, 123);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
