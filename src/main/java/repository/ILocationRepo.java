package repository;

import dtos.LocationDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ILocationRepo {
    LocationDTO deleteLocation(int id) throws IOException, URISyntaxException;
    LocationDTO createLocation(LocationDTO locationDTO) throws IOException, URISyntaxException;
    List<LocationDTO> getAllLocations() throws IOException, URISyntaxException;

}
