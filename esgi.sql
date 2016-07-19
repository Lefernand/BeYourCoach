-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Mar 19 Juillet 2016 à 23:54
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `esgi`
--

-- --------------------------------------------------------

--
-- Structure de la table `aliments`
--

CREATE TABLE `aliments` (
  `id` int(11) NOT NULL,
  `nom` text NOT NULL,
  `image` text NOT NULL,
  `energie` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `time` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `aliments`
--

INSERT INTO `aliments` (`id`, `nom`, `image`, `energie`, `id_user`, `time`, `date`, `quantite`) VALUES
(1, '0', 'image', 22, 1, 'PDJ', '2016-07-12', 1),
(2, 'Boisson rafrachissante au jus d''orange', 'http://static.openfoodfacts.org/images/products/544/900/001/1527/front_fr.33.400.jpg', 166, 5, 'PDJ', '2016-07-19', 1),
(3, 'Vinaigrette nature allge en matires grasses', 'http://static.openfoodfacts.org/images/products/20257798/front_fr.14.400.jpg', 1029, 5, 'PDJ', '2016-07-19', 1),
(4, 'Coulis de tomates (Pure de tomates mi-rduite  11%)', 'http://static.openfoodfacts.org/images/products/560/101/900/1030/front_fr.21.400.jpg', 158, 5, 'PDJ', '2016-07-19', 1),
(5, 'Sauce tomate au basilic', 'http://static.openfoodfacts.org/images/products/807/680/951/3722/front_fr.10.400.jpg', 255, 5, 'PDJ', '2016-07-19', 1);

-- --------------------------------------------------------

--
-- Structure de la table `suivi_poids`
--

CREATE TABLE `suivi_poids` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `poids` float NOT NULL,
  `IMC` float NOT NULL,
  `MG` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `suivi_poids`
--

INSERT INTO `suivi_poids` (`id`, `id_user`, `poids`, `IMC`, `MG`, `date`) VALUES
(14, 5, 6, 0, 0, '2016-07-01'),
(15, 5, 6, 0, 0, '2016-07-01'),
(16, 8, 88, 0, 0, '2016-07-01'),
(17, 8, 88, 0, 0, '2016-07-01'),
(18, 5, 444, 0, 0, '2016-07-02'),
(19, 5, 444, 0, 0, '2016-07-02'),
(20, 5, 44, 0, 0, '2016-07-16'),
(21, 5, 44, 0, 0, '2016-07-16'),
(22, 5, 44, 0, 0, '2016-07-16'),
(23, 33, 33, 33, 33, '2016-07-27'),
(35, 5, 76, 23.4568, 17.4681, '2016-07-19');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `sexe` tinyint(1) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'user',
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `int_taille` int(11) DEFAULT NULL,
  `objectif_poids` int(11) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `date_creation` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `sexe`, `login`, `email`, `password`, `role`, `nom`, `prenom`, `int_taille`, `objectif_poids`, `date_naissance`, `date_creation`) VALUES
(5, 1, 'admin', 'admin@admin.fr', 'admin', 'admin', 'Administrateur', 'Administrateur', 180, 1, '1992-01-11', NULL),
(6, 0, 'user', 'user@user.fr', 'user', 'user', NULL, NULL, NULL, NULL, NULL, NULL),
(7, 1, 'test', 'test@test.fr', 'test', 'admin', 'Alex', 'Dupain', 180, 74, '1992-01-11', '2016-07-01'),
(8, 0, 'johnson', 'johnson@johnson.fr', 'johnson', 'user', 'johnson', 'johnson', 180, 75, '2016-01-23', '2016-07-01'),
(10, NULL, 'tata', 'tata@gmail.com', 'tata', 'user', NULL, NULL, NULL, NULL, NULL, '2016-07-17'),
(11, NULL, 'toto', 'tata@gmail.com', 'tata', 'user', NULL, NULL, NULL, NULL, NULL, '2016-07-17'),
(12, 1, 'tutu2', 'tutu2@gmail.com', 'tutu2', 'user2', 'tutu', 'tutu', 2, 2, '2016-07-12', '2016-07-18');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `aliments`
--
ALTER TABLE `aliments`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `suivi_poids`
--
ALTER TABLE `suivi_poids`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `aliments`
--
ALTER TABLE `aliments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `suivi_poids`
--
ALTER TABLE `suivi_poids`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
