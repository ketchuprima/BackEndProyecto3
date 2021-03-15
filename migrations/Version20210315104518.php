<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210315104518 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE candidat_oferta');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE candidat_oferta (id INT AUTO_INCREMENT NOT NULL, id_candidat_id INT NOT NULL, id_oferta_id INT NOT NULL, hora DATETIME NOT NULL, INDEX IDX_CAB2358E7ACE4A03 (id_oferta_id), INDEX IDX_CAB2358E10C22675 (id_candidat_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358E10C22675 FOREIGN KEY (id_candidat_id) REFERENCES candidat (id)');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358E7ACE4A03 FOREIGN KEY (id_oferta_id) REFERENCES oferta (id)');
    }
}
