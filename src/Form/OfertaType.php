<?php

namespace App\Form;

use App\Entity\Oferta;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class OfertaType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('estat')
            ->add('titol')
            ->add('descripcio')
            ->add('data_de_publicacio')
            ->add('ubicacio')
            ->add('id_categoria')
            ->add('empresa')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Oferta::class,
        ]);
    }
}
