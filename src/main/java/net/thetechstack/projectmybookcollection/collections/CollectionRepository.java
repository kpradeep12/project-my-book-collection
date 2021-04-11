package net.thetechstack.projectmybookcollection.collections;

import net.thetechstack.projectmybookcollection.readers.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, String> {}
