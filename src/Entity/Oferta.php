<?php

namespace App\Entity;

use App\Repository\OfertaRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=OfertaRepository::class)
 */
class Oferta
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $estat;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $titol;

    /**
     * @ORM\Column(type="string", length=1000)
     */
    private $descripcio;

    /**
     * @ORM\Column(type="datetime")
     */
    private $data_de_publicacio;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $ubicacio;

    /**
     * @ORM\OneToOne(targetEntity=Categoria::class, cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $id_categoria;

    /**
     * @ORM\ManyToOne(targetEntity=Empresa::class, inversedBy="ofertes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $empresa;

    /**
     * @ORM\OneToMany(targetEntity=CandidatOferta::class, mappedBy="oferta")
     */
    private $candidats;

    public function __construct()
    {
        $this->candidats = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEstat(): ?int
    {
        return $this->estat;
    }

    public function setEstat(int $estat): self
    {
        $this->estat = $estat;

        return $this;
    }

    public function getTitol(): ?string
    {
        return $this->titol;
    }

    public function setTitol(string $titol): self
    {
        $this->titol = $titol;

        return $this;
    }

    public function getDescripcio(): ?string
    {
        return $this->descripcio;
    }

    public function setDescripcio(string $descripcio): self
    {
        $this->descripcio = $descripcio;

        return $this;
    }

    public function getDataDePublicacio(): ?\DateTimeInterface
    {
        return $this->data_de_publicacio;
    }

    public function setDataDePublicacio(\DateTimeInterface $data_de_publicacio): self
    {
        $this->data_de_publicacio = $data_de_publicacio;

        return $this;
    }

    public function getUbicacio(): ?string
    {
        return $this->ubicacio;
    }

    public function setUbicacio(string $ubicacio): self
    {
        $this->ubicacio = $ubicacio;

        return $this;
    }

    public function getIdCategoria(): ?Categoria
    {
        return $this->id_categoria;
    }

    public function setIdCategoria(Categoria $id_categoria): self
    {
        $this->id_categoria = $id_categoria;

        return $this;
    }

    public function getEmpresa(): ?Empresa
    {
        return $this->empresa;
    }

    public function setEmpresa(?Empresa $empresa): self
    {
        $this->empresa = $empresa;

        return $this;
    }

    /**
     * @return Collection|CandidatOferta[]
     */
    public function getCandidats(): Collection
    {
        return $this->candidats;
    }

    public function addCandidat(CandidatOferta $candidat): self
    {
        if (!$this->candidats->contains($candidat)) {
            $this->candidats[] = $candidat;
            $candidat->setOferta($this);
        }

        return $this;
    }

    public function removeCandidat(CandidatOferta $candidat): self
    {
        if ($this->candidats->removeElement($candidat)) {
            // set the owning side to null (unless already changed)
            if ($candidat->getOferta() === $this) {
                $candidat->setOferta(null);
            }
        }

        return $this;
    }
}
