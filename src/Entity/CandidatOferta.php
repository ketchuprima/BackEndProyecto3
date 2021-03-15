<?php

namespace App\Entity;

use App\Repository\CandidatOfertaRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatOfertaRepository::class)
 */
class CandidatOferta
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=Candidat::class, inversedBy="ofertes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $candidat;

    /**
     * @ORM\ManyToOne(targetEntity=Oferta::class, inversedBy="candidats")
     * @ORM\JoinColumn(nullable=false)
     */
    private $oferta;

    /**
     * @ORM\Column(type="datetime")
     */
    private $hora;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCandidat(): ?Candidat
    {
        return $this->candidat;
    }

    public function setCandidat(?Candidat $candidat): self
    {
        $this->candidat = $candidat;

        return $this;
    }

    public function getOferta(): ?Oferta
    {
        return $this->oferta;
    }

    public function setOferta(?Oferta $oferta): self
    {
        $this->oferta = $oferta;

        return $this;
    }

    public function getHora(): ?\DateTimeInterface
    {
        return $this->hora;
    }

    public function setHora(\DateTimeInterface $hora): self
    {
        $this->hora = $hora;

        return $this;
    }
}
