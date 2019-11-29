package web.biz.vanityFair.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import web.biz.vanityFair.domain.file.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long>, CrudRepository<File, Long> {

    Optional<File> findByFileCd(String fileCd);
}
