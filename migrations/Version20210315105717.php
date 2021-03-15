<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210315105717 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE candidat_oferta (id INT AUTO_INCREMENT NOT NULL, candidat_id INT NOT NULL, oferta_id INT NOT NULL, hora DATETIME NOT NULL, INDEX IDX_CAB2358E8D0EB82 (candidat_id), INDEX IDX_CAB2358EFAFBF624 (oferta_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358E8D0EB82 FOREIGN KEY (candidat_id) REFERENCES candidat (id)');
        $this->addSql('ALTER TABLE candidat_oferta ADD CONSTRAINT FK_CAB2358EFAFBF624 FOREIGN KEY (oferta_id) REFERENCES oferta (id)');
        $this->addSql('DROP TABLE oferta_candidat');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE oferta_candidat (oferta_id INT NOT NULL, candidat_id INT NOT NULL, INDEX IDX_BC759CA0FAFBF624 (oferta_id), INDEX IDX_BC759CA08D0EB82 (candidat_id), PRIMARY KEY(oferta_id, candidat_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE oferta_candidat ADD CONSTRAINT FK_BC759CA08D0EB82 FOREIGN KEY (candidat_id) REFERENCES candidat (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE oferta_candidat ADD CONSTRAINT FK_BC759CA0FAFBF624 FOREIGN KEY (oferta_id) REFERENCES oferta (id) ON DELETE CASCADE');
        $this->addSql('DROP TABLE candidat_oferta');
    }
}
