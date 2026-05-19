package com.simplecore.erp.gui.svg;

import java.awt.Dimension;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import com.kitfox.svg.app.beans.SVGIcon;
import java.net.URISyntaxException;
import java.net.URL;

public class CustomSVGIcon extends SVGIcon {

    public CustomSVGIcon(String svgPath, Dimension dimension) {
        // Get the resource URL
        URL resource = getClass().getResource(svgPath);
        if (resource == null) {
            throw new IllegalArgumentException("Cannot find SVG file: " + svgPath);
        }

        int size = (int) Math.min(dimension.getWidth(), dimension.getHeight());
        // If size is odd, reduce by 1 to make it even
        if (size % 2 != 0) {
            size--;
        }
        
        try {
            // Convert URL to URI and set the SVG source
            setSvgURI(resource.toURI());

            // Configure icon properties
            setAntiAlias(true);  // Enable anti-aliasing for better quality
            setAutosize(AUTOSIZE_STRETCH);   // Enable auto-resizing
            setPreferredSize(new Dimension(size, size)); // Set preferred size
            

        } catch (URISyntaxException e) {
            throw new RuntimeException("Error converting URL to URI: " + e.getMessage(), e);
        }
    }
}
