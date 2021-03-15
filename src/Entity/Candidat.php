<?php

namespace App\Entity;

use App\Repository\CandidatRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatRepository::class)
 */
class Candidat
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=100)
     */
    private $cognoms;

    /**
     * @ORM\Column(type="integer")
     */
    private $telefon;

    /**
     * @ORM\OneToOne(targetEntity=User::class, cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $usuari;

    /**
     * @ORM\OneToMany(targetEntity=CandidatOferta::class, mappedBy="candidat")
     */
    private $ofertes;

    public function __construct()
    {
        $this->ofertes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getCognoms(): ?string
    {
        return $this->cognoms;
    }

    public function setCognoms(string $cognoms): self
    {
        $this->cognoms = $cognoms;

        return $this;
    }

    public function getTelefon(): ?int
    {
        return $this->telefon;
    }

    public function setTelefon(int $telefon): self
    {
        $this->telefon = $telefon;

        return $this;
    }

    public function getUsuari(): ?User
    {
        return $this->usuari;
    }

    public function setUsuari(User $usuari): self
    {
        $this->usuari = $usuari;

        return $this;
    }

    /**
     * @return Collection|CandidatOferta[]
     */
    public function getOfertes(): Collection
    {
        return $this->ofertes;
    }

    public function addOferte(CandidatOferta $oferte): self
    {
        if (!$this->ofertes->contains($oferte)) {
            $this->ofertes[] = $oferte;
            $oferte->setCandidat($this);
        }

        return $this;
    }

    public function removeOferte(CandidatOferta $oferte): self
    {
        if ($this->ofertes->removeElement($oferte)) {
            // set the owning side to null (unless already changed)
            if ($oferte->getCandidat() === $this) {
                $oferte->setCandidat(null);
            }
        }

        return $this;
    }
}
