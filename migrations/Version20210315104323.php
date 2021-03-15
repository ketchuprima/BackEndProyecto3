<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210315104323 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE candidat (id INT AUTO_INCREMENT NOT NULL, usuari_id INT NOT NULL, nom VARCHAR(50) NOT NULL, cognoms VARCHAR(100) NOT NULL, telefon INT NOT NULL, UNIQUE INDEX UNIQ_6AB5B4715F263030 (usuari_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE candidat_oferta (id INT AUTO_INCREMENT NOT NULL, id_candidat_id INT NOT NULL, id_oferta_id INT NOT NULL, hora DATETIME NOT NULL, INDEX IDX_CAB2358E10C22675 (id_candidat_id), INDEX IDX_CAB2358E7ACE4A03 (id_oferta_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE categoria (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(40) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE empresa (id INT AUTO_INCREMENT NOT NULL, usuari_id INT NOT NULL, nom VARCHAR(255) NOT NULL, tipus VARCHAR(255) NOT NULL, logo VARCHAR(500) NOT NULL, correu VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_B8D75A505F263030 (usuari_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE oferta_candidat (oferta_id INT NOT NULL, candidat_id INT NOT NULL, INDEX IDX_BC759CA0FAFBF624 (oferta_id), INDEX IDX_BC759CA08D0EB82 (candidat_id), PRIMARY KEY(oferta_id, candidat_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) NOT NULL, roles VARCHAR(255) NOT NULL, pass VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE candidat ADD CONSTRAINT FK_6AB5B4715F263030 FOREIGN KEY (usuari_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358E10C22675 FOREIGN KEY (id_candidat_id) REFERENCES candidat (id)');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358E7ACE4A03 FOREIGN KEY (id_oferta_id) REFERENCES oferta (id)');
        $this->addSql('ALTER TABLE empresa ADD CONSTRAINT FK_B8D75A505F263030 FOREIGN KEY (usuari_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE oferta_candidat ADD CONSTRAINT FK_BC759CA0FAFBF624 FOREIGN KEY (oferta_id) REFERENCES oferta (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE oferta_candidat ADD CONSTRAINT FK_BC759CA08D0EB82 FOREIGN KEY (candidat_id) REFERENCES candidat (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE oferta ADD id_categoria_id INT NOT NULL, ADD empresa_id INT NOT NULL');
        $this->addSql('ALTER TABLE oferta ADD CONSTRAINT FK_7479C8F210560508 FOREIGN KEY (id_categoria_id) REFERENCES categoria (id)');
        $this->addSql('ALTER TABLE oferta ADD CONSTRAINT FK_7479C8F2521E1991 FOREIGN KEY (empresa_id) REFERENCES empresa (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_7479C8F210560508 ON oferta (id_categoria_id)');
        $this->addSql('CREATE INDEX IDX_7479C8F2521E1991 ON oferta (empresa_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE candidat_oferta DROP FOREIGN KEY FK_CAB2358E10C22675');
        $this->addSql('ALTER TABLE oferta_candidat DROP FOREIGN KEY FK_BC759CA08D0EB82');
        $this->addSql('ALTER TABLE oferta DROP FOREIGN KEY FK_7479C8F210560508');
        $this->addSql('ALTER TABLE oferta DROP FOREIGN KEY FK_7479C8F2521E1991');
        $this->addSql('ALTER TABLE candidat DROP FOREIGN KEY FK_6AB5B4715F263030');
        $this->addSql('ALTER TABLE empresa DROP FOREIGN KEY FK_B8D75A505F263030');
        $this->addSql('DROP TABLE candidat');
        $this->addSql('DROP TABLE candidat_oferta');
        $this->addSql('DROP TABLE categoria');
        $this->addSql('DROP TABLE empresa');
        $this->addSql('DROP TABLE oferta_candidat');
        $this->addSql('DROP TABLE user');
        $this->addSql('DROP INDEX UNIQ_7479C8F210560508 ON oferta');
        $this->addSql('DROP INDEX IDX_7479C8F2521E1991 ON oferta');
        $this->addSql('ALTER TABLE oferta DROP id_categoria_id, DROP empresa_id');
    }
}
