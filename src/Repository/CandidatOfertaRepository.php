<?php

namespace App\Repository;

use App\Entity\CandidatOferta;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method CandidatOferta|null find($id, $lockMode = null, $lockVersion = null)
 * @method CandidatOferta|null findOneBy(array $criteria, array $orderBy = null)
 * @method CandidatOferta[]    findAll()
 * @method CandidatOferta[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CandidatOfertaRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, CandidatOferta::class);
    }

    // /**
    //  * @return CandidatOferta[] Returns an array of CandidatOferta objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?CandidatOferta
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
