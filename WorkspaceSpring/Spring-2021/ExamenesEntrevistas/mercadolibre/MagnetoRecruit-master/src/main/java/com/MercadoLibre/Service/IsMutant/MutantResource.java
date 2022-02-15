package com.MercadoLibre.Service.IsMutant;

import com.MercadoLibre.Service.Utils.ValidDna;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ismutant")
public class MutantResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Dna> isMutant() {
        String[] aux = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Dna dna = new Dna();
        dna.setDna(aux);
        return ResponseEntity.ok(dna);
    }
    
    @ApiOperation(value = "Service for determinate if mutant")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> isMutant(@RequestBody Dna dna) {

        try {
            String[] Auxdna = new String[dna.getDna().length];
            System.arraycopy(dna.getDna(), 0, Auxdna, 0, dna.getDna().length);

            if (ValidDna.isValidDna(Auxdna)) {

                if (Mutant.isMutant(Auxdna)) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        
        } catch (RuntimeException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     
    }

}
